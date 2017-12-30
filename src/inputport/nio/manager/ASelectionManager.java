package inputport.nio.manager;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

import util.trace.Tracer;



public class ASelectionManager implements SelectionManager {
//	Queue<RequestResponse> requestQueue = new ConcurrentLinkedQueue();	
//	List<WriteCommand> nextBufferedWrites = new ArrayList();
	Selector selector ;
	SelectionReadManager readManager;
	SelectionWriteManager writeManager;
	SelectionConnectionManager connectionManager;
//	Map<SelectableChannel, Response> channelToResponse = new HashMap();
//	Map<SelectableChannel, SocketChannelCloseListener> channelToCloseListener = new HashMap();
//	// why not a single queue
//	// why not selectable channel
//	Map<SocketChannel, WriteBoundedBuffer> channelToBufferedWriteBoundedBuffer = new Hashtable();	
//
//	Map<SocketChannel, ReadCommand> channelToReadHandler = new HashMap();	
//	
	public ASelectionManager () {
		try {
			selector = SelectorProvider.provider().openSelector();
			readManager = createSelectionReadManager();
			writeManager = createSelectionWriteManager();
			connectionManager = createSelectionConnectionManager();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected SelectionReadManager createSelectionReadManager() {
		return new ASelectionReadManager(this);
	}
	protected SelectionWriteManager createSelectionWriteManager() {
		return new ASelectionWriteManager(this);
	}
	protected SelectionConnectionManager createSelectionConnectionManager() {
		return new ASelectionConnectionManager(this);
	}
//	protected ReadCommand createReadHandler(SocketChannel theSocketChannel) {
//		return new AReadCommand(this, theSocketChannel);
//	}
//	protected void allocateReadState(SocketChannel theSocketChannel) {
//		ReadCommand readHandler = createReadHandler(theSocketChannel);
//		channelToReadHandler.put(theSocketChannel, readHandler);
//	
//		
//	}
//	@Override
//	public ReadCommand getReadHandler(SocketChannel theSocketChannel) {
//		ReadCommand readVal = channelToReadHandler.get(theSocketChannel);
//		if (readVal == null) 
//			allocateReadState(theSocketChannel);
//		return channelToReadHandler.get(theSocketChannel);			
//	}
	
//	public WriteBoundedBuffer getBufferedWriteBoundedBuffer(WriteCommand bufferedWrite) {
//
//		SocketChannel channel = bufferedWrite.getSocketChannel();
//		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(channel);
//
//		if (bufferedWriteBoundedBuffer == null) {
//			bufferedWriteBoundedBuffer =  new AWriteBoundedBuffer(this, channel);
//
//			channelToBufferedWriteBoundedBuffer.put(channel, bufferedWriteBoundedBuffer);
//		}
//		return bufferedWriteBoundedBuffer;
//	}
//	
//	public synchronized void putBufferedWrite (WriteCommand bufferedWrite) {
//		try {
//			Tracer.info(this, "Started storing of buffered write with id:" + bufferedWrite.getId());
//			WriteBoundedBuffer bufferedWriteBoundedBuffer = getBufferedWriteBoundedBuffer(bufferedWrite);
//
//			bufferedWriteBoundedBuffer.put(bufferedWrite);	
//			if (registerWriteOpsForBufferedWrites(bufferedWrite)) {
//				Tracer.info(this, "Waking up selector as new write registered");
//				selector.wakeup();
//			}
////			Tracer.info(this, "Finished storing of buffered write with id:" + bufferedWrite.getId());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}	
//	public synchronized void putRequestResponse (RequestResponse request) {	
//		Tracer.info(this, "entering synchronized putRequestResponse");
//
//		requestQueue.add(request);
//		Tracer.info(this, "Waking up selector after adding command:" + request);
//		selector.wakeup();
//		Tracer.info(this, "leaving synchronized putRequestResponse");
//
//	}		
	public Selector getSelector() {
		return selector;
	}
//	synchronized void  preProcessConnectAndAccepts() {
//		Tracer.info(this, "entering synchronized preProcessConnectAndAccepts");
//
//		// synchronized because while processing, what if new request added
//		for (Request request:requestQueue) {
//			Tracer.info(this, "Calling makeRequest on:" + request);
//			request.initiate(); 
//			Tracer.info(this, "Associating request  with channel to wait for response :" + request.getChannel());
//			channelToResponse.put(request.getChannel(), (Response) request);			
//		}
//		requestQueue.clear();
//		Tracer.info(this, "leaving synchronized preProcessConnectAndAccepts");
//
//	}

//	protected void processBufferedWrites() {
//		for (WriteBoundedBuffer bufferedWriteBoundedBuffer: channelToBufferedWriteBoundedBuffer.values()) {
//
//			processChannelBufferedWrites(bufferedWriteBoundedBuffer);
//
//			
//		}
//	}
//	boolean registerWriteOpsForBufferedWrites(WriteCommand writeBuffer) {
//		return makeRequestForWriteBoundedBuffer(writeBuffer.getSocketChannel());
//	}
//	@Override
//	 boolean makeRequestForWriteBoundedBuffer(SocketChannel channel) {
//		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(channel);
//		return bufferedWriteBoundedBuffer.initiate();
//
//		
//	}
//	 // keep getting concurrent change exception without synchronized
//	synchronized void  preProcessBufferedWrites() {
//		Tracer.info(this, "entering synchronized preProcessBufferedWrites");
//
//
//		for (SocketChannel channel: channelToBufferedWriteBoundedBuffer.keySet()) {
//			if (!makeRequestForWriteBoundedBuffer(channel))
//				Tracer.info(this, "channel not connected or no pending writes:" + channel);
//		}	
//		
//		Tracer.info(this, "leaving synchronized preProcessBufferedWrites");
//
//	}

//	void  processChannelBufferedWrites(SelectionKey selectionKey) {	
//		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(selectionKey.channel());
//		// could the bounded buffer be null?
//		processChannelBufferedWrites(bufferedWriteBoundedBuffer);		
//	}
//	 void  processChannelBufferedWrites(WriteBoundedBuffer bufferedWriteBoundedBuffer) {	
//		for (WriteCommand bufferedWrite: bufferedWriteBoundedBuffer) {
//			if (bufferedWrite.execute())
//				bufferedWriteBoundedBuffer.remove(bufferedWrite);
//			else //  break out of inner loop as the last bufferedWrite could not finish
//				break;
//		}		
//	}
	synchronized void processSelectedOperation() {
		Tracer.info(this, "entering synchronized processSelectedOperation");

		for (SelectionKey selectionKey: selector.selectedKeys()) {
//			if (!selectionKey.isValid()) break;
			if (!selectionKey.isValid()) {
				Tracer.warning("selection key" + selectionKey + " is invalid. Hopefully channel disconnected message will be sent on the next read");
				continue; 
			}

			if (selectionKey.isAcceptable() || selectionKey.isConnectable()) {
				Response response = connectionManager.get(selectionKey.channel());
				Tracer.info(this, "Received connection accept/connect event on channel " + selectionKey.channel());	
				Tracer.info(this, "Calling giveResponse on:" + response);
				response.execute();
//				Tracer.info(this, "Finished calling handler for accept.connect event");
				//selectionKey.interestOps(SelectionKey.OP_READ);
			}
			else if (selectionKey.isReadable()) {

					readManager.processRead(selectionKey);

			}

			else if (selectionKey.isWritable()) {
//				WriteBoundedBuffer bufferedWriteBoundedBuffer = writeManager.get((SocketChannel) selectionKey.channel());
				writeManager.processChannelBufferedWrites(selectionKey);

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
				connectionManager.preProcessConnectAndAccepts();
				Tracer.info(this, "Selector preprocessing write requests");
				writeManager.preProcessBufferedWrites();
				Tracer.info(this, "Selector calls select");
				selector.select();
				Tracer.info(this, "Selector select unblocks");
				processSelectedOperation();			
			} catch (Exception e) {
				// Disconnect does not seem to come here
				e.printStackTrace();
				System.err.println("Process select operation exception:" + e.getMessage());
			}	
		}
	}
//	@Override
//	public void close(SelectionKey selectionKey, Exception readException)  {
//		try {
//			Tracer.info(this, "Closing and cancelling selection key for:" +  selectionKey.channel());
//			selectionKey.channel().close();			
//			selectionKey.cancel();
//			notifyClose((SocketChannel) selectionKey.channel(), readException);
//			return;
//		} catch (IOException e) {
//			e.printStackTrace();			
//		}
//	}
//
//	protected boolean processRead(SelectionKey theSelectionKey) /*throws IOException*/ {
//		
//		SocketChannel socketChannel = (SocketChannel) theSelectionKey.channel();
//		return getReadHandler(socketChannel).execute();
//
//	}
//
//	void notifyClose(SocketChannel theSocketChannel, Exception readException) {
//		SocketChannelCloseListener closeListener = channelToCloseListener.get(theSocketChannel);
//
//		
//	}
//	
//	
//	@Override
//	public void registerReadListener(SocketChannel theChannel, SocketChannelReadListener theReadListener) {
//		getReadHandler(theChannel).addReadListener(theReadListener);
////		channelToReadListener.put(theChannel, theReadListener);		
//	}
//	
//	@Override
//	public void registerCloseListener(SelectableChannel aChannel, SocketChannelCloseListener aCloseListener) {
//		channelToCloseListener.put(aChannel, aCloseListener);		
//	}
	@Override
	public void close(SelectionKey selectionKey, Exception readException) {
		connectionManager.close(selectionKey, readException);
		
	}
	@Override
	public ReadCommand getReadHandler(SocketChannel theSocketChannel) {
		return readManager.getReadHandler(theSocketChannel);
	}
	@Override
	public WriteBoundedBuffer getWriteBoundedBuffer(SocketChannel aSocketChannel) {
		return writeManager.getBufferedWriteBoundedBuffer(aSocketChannel);
	}
	@Override
	public void putBufferedWrite(WriteCommand bufferedWrite) {
		writeManager.putBufferedWrite(bufferedWrite);
		
	}
	@Override
	public void putRequestResponse(RequestResponse request) {
		connectionManager.putRequestResponse(request);
		
	}
	@Override
	public void registerCloseListener(SelectableChannel aChannel,
			SocketChannelCloseListener aCloseListener) {
		connectionManager.registerCloseListener(aChannel, aCloseListener);
		
	}
	@Override
	public void registerReadListener(SocketChannel theChannel,
			SocketChannelReadListener theReadListener) {
		readManager.registerReadListener(theChannel, theReadListener);
		
	}
	
	

}
