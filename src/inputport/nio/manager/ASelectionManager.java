package inputport.nio.manager;

import inputport.nio.manager.commands.ReadCommand;
import inputport.nio.manager.commands.RequestResponse;
import inputport.nio.manager.commands.Response;
import inputport.nio.manager.commands.WriteCommand;
import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

import util.trace.Tracer;
import util.trace.port.nio.SelectCalled;
import util.trace.port.nio.SelectUnblocked;



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
		
	public Selector getSelector() {
		return selector;
	}

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
				SelectCalled.newCase(this, selector);
				selector.select();
				SelectUnblocked.newCase(this, selector);
				Tracer.info(this, "Selector select unblocks");
				processSelectedOperation();			
			} catch (Exception e) {
				// Disconnect does not seem to come here
				e.printStackTrace();
				System.err.println("Process select operation exception:" + e.getMessage());
			}	
		}
	}

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
