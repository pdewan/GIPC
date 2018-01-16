package inputport.nio.manager;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import util.trace.Tracer;
import util.trace.port.nio.ReadRequestCreated;
import util.trace.port.nio.SelectorWokenUp;



public class ASingleMonitorSelectionManager implements SelectionManager {
//	public static final int READ_BUFFER_SIZE = 8192;
	Queue<RequestResponse> requestQueue = new ConcurrentLinkedQueue();	
	List<WriteCommand> nextBufferedWrites = new ArrayList();
	Selector selector ;
	Map<SelectableChannel, Response> channelToResponse = new HashMap();
//	Map<SelectableChannel, SocketChannelReadListener> channelToReadListener = new HashMap();
	Map<SelectableChannel, SocketChannelCloseListener> channelToCloseListener = new HashMap();
	// why not a single queue
	// why not selectable channel
//	Map<SocketChannel, BlockingQueue<BufferedWrite>> channelToBufferedWriteBoundedBuffer = new Hashtable();	
	Map<SocketChannel, WriteBoundedBuffer> channelToBufferedWriteBoundedBuffer = new Hashtable();	

	Map<SocketChannel, ReadCommand> channelToReadHandler = new HashMap();	
	
	public ASingleMonitorSelectionManager () {
		try {
			selector = SelectorProvider.provider().openSelector();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected ReadCommand createReadHandler(SocketChannel theSocketChannel) {
		ReadCommand retVal = new AReadCommand(this, theSocketChannel);
		ReadRequestCreated.newCase(this, retVal);
		return retVal;
	}
	protected void allocateReadState(SocketChannel theSocketChannel) {
		ReadCommand readHandler = createReadHandler(theSocketChannel);
		channelToReadHandler.put(theSocketChannel, readHandler);
	
		
	}
	@Override
	public ReadCommand getReadHandler(SocketChannel theSocketChannel) {
		ReadCommand readVal = channelToReadHandler.get(theSocketChannel);
		if (readVal == null) 
			allocateReadState(theSocketChannel);
		return channelToReadHandler.get(theSocketChannel);			
	}
	
	public WriteBoundedBuffer getBufferedWriteBoundedBuffer(WriteCommand bufferedWrite) {

		SocketChannel channel = bufferedWrite.getSocketChannel();
		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(channel);

		if (bufferedWriteBoundedBuffer == null) {
			bufferedWriteBoundedBuffer =  new AWriteBoundedBuffer(this, channel);

			channelToBufferedWriteBoundedBuffer.put(channel, bufferedWriteBoundedBuffer);
		}
		return bufferedWriteBoundedBuffer;
	}
	
	public synchronized void putBufferedWrite (WriteCommand bufferedWrite) {
		try {
			Tracer.info(this, "Started storing of buffered write with id:" + bufferedWrite.getId());
			WriteBoundedBuffer bufferedWriteBoundedBuffer = getBufferedWriteBoundedBuffer(bufferedWrite);

			bufferedWriteBoundedBuffer.put(bufferedWrite);	
			if (registerWriteOpsForBufferedWrites(bufferedWrite)) {
				Tracer.info(this, "Waking up selector as new write registered");
				SelectorWokenUp.newCase(this, selector);
				selector.wakeup();
			}
//			Tracer.info(this, "Finished storing of buffered write with id:" + bufferedWrite.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public synchronized void putRequestResponse (RequestResponse request) {	
		Tracer.info(this, "entering synchronized putRequestResponse");

		requestQueue.add(request);
		Tracer.info(this, "Waking up selector after adding command:" + request);
		selector.wakeup();
		Tracer.info(this, "leaving synchronized putRequestResponse");

	}		
	public Selector getSelector() {
		return selector;
	}
	synchronized void  preProcessConnectAndAccepts() {
		Tracer.info(this, "entering synchronized preProcessConnectAndAccepts");

		// synchronized because while processing, what if new request added
		for (Request request:requestQueue) {
			Tracer.info(this, "Calling makeRequest on:" + request);
			request.initiate(); 
			Tracer.info(this, "Associating request  with channel to wait for response :" + request.getChannel());
			channelToResponse.put(request.getChannel(), (Response) request);			
		}
		requestQueue.clear();
		Tracer.info(this, "leaving synchronized preProcessConnectAndAccepts");

	}

	protected void processBufferedWrites() {
		for (WriteBoundedBuffer bufferedWriteBoundedBuffer: channelToBufferedWriteBoundedBuffer.values()) {

			processChannelBufferedWrites(bufferedWriteBoundedBuffer);

			
		}
	}
	boolean registerWriteOpsForBufferedWrites(WriteCommand writeBuffer) {
		return makeRequestForWriteBoundedBuffer(writeBuffer.getSocketChannel());
	}
//	@Override
	 boolean makeRequestForWriteBoundedBuffer(SocketChannel channel) {
		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(channel);
		return bufferedWriteBoundedBuffer.initiate();

		
	}
	 // keep getting concurrent change exception without synchronized
	synchronized void  preProcessBufferedWrites() {
		Tracer.info(this, "entering synchronized preProcessBufferedWrites");


		for (SocketChannel channel: channelToBufferedWriteBoundedBuffer.keySet()) {
			if (!makeRequestForWriteBoundedBuffer(channel))
				Tracer.info(this, "channel not connected or no pending writes:" + channel);
		}	
		
		Tracer.info(this, "leaving synchronized preProcessBufferedWrites");

	}

	void  processChannelBufferedWrites(SelectionKey selectionKey) {	
		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(selectionKey.channel());
		// could the bounded buffer be null?
		processChannelBufferedWrites(bufferedWriteBoundedBuffer);		
	}
	 void  processChannelBufferedWrites(WriteBoundedBuffer bufferedWriteBoundedBuffer) {	
		for (WriteCommand bufferedWrite: bufferedWriteBoundedBuffer) {
			if (bufferedWrite.execute())
				bufferedWriteBoundedBuffer.remove(bufferedWrite);
			else //  break out of inner loop as the last bufferedWrite could not finish
				break;
		}		
	}
	synchronized void processSelectedOperation() {
		Tracer.info(this, "entering synchronized processSelectedOperation");

		for (SelectionKey selectionKey: selector.selectedKeys()) {
			if (!selectionKey.isValid()) break;
			if (selectionKey.isAcceptable() || selectionKey.isConnectable()) {
				Response response = channelToResponse.get(selectionKey.channel());
				Tracer.info(this, "Received connection accept/connect event on channel " + selectionKey.channel());	
				Tracer.info(this, "Calling giveResponse on:" + response);
				response.execute();
//				Tracer.info(this, "Finished calling handler for accept.connect event");
				//selectionKey.interestOps(SelectionKey.OP_READ);
			}
			else if (selectionKey.isReadable()) {

					processRead(selectionKey);

			}

			else if (selectionKey.isWritable()) {
				WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(selectionKey.channel());
				processChannelBufferedWrites(bufferedWriteBoundedBuffer);

			}					
		}
		selector.selectedKeys().clear();
		Tracer.info(this, "leaving synchronized processSelectedOperation");

		
	}
	@Override
	public void run() {		
		// executed after each wakeup event
		while (true) {
			try {
				Tracer.info(this, "Selector preprocessing connect/accept request");
				preProcessConnectAndAccepts();
				Tracer.info(this, "Selector preprocessing write requests");
				preProcessBufferedWrites();
				Tracer.info(this, "Selector calls select");
				selector.select();
				Tracer.info(this, "Selector select unblocks");
				processSelectedOperation();			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	@Override
	public void close(SelectionKey selectionKey, Exception readException)  {
		try {
			Tracer.info(this, "Closing and cancelling selection key for:" +  selectionKey.channel());
			selectionKey.channel().close();			
			selectionKey.cancel();
			notifyClose((SocketChannel) selectionKey.channel(), readException);
			return;
		} catch (IOException e) {
			e.printStackTrace();			
		}
	}

	protected boolean processRead(SelectionKey theSelectionKey) /*throws IOException*/ {
		
		SocketChannel socketChannel = (SocketChannel) theSelectionKey.channel();
		return getReadHandler(socketChannel).execute();

	}

	void notifyClose(SocketChannel theSocketChannel, Exception readException) {
		SocketChannelCloseListener closeListener = channelToCloseListener.get(theSocketChannel);

		
	}
	
	
	@Override
	public void registerReadListener(SocketChannel theChannel, SocketChannelReadListener theReadListener) {
		getReadHandler(theChannel).addReadListener(theReadListener);
//		channelToReadListener.put(theChannel, theReadListener);		
	}
	
	@Override
	public void registerCloseListener(SelectableChannel aChannel, SocketChannelCloseListener aCloseListener) {
		channelToCloseListener.put(aChannel, aCloseListener);		
	}
	@Override
	public WriteBoundedBuffer getWriteBoundedBuffer(SocketChannel aSocketChannel) {
		return channelToBufferedWriteBoundedBuffer.get(aSocketChannel);

	}
	
	

}
