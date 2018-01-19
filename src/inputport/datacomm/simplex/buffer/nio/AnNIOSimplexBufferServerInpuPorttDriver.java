package inputport.datacomm.simplex.buffer.nio;
import inputport.datacomm.simplex.buffer.SimplexServerInputPortSkeleton;
import inputport.nio.manager.AnNIOManager;
import inputport.nio.manager.NIOManager;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.factories.classes.AReadingAcceptCommandFactory;
import inputport.nio.manager.factories.selectors.AcceptCommandFactorySelector;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;



public class AnNIOSimplexBufferServerInpuPorttDriver implements NIOSimplexServerInputDriver  {
	SimplexServerInputPortSkeleton<ServerSocketChannel, SocketChannel> skeleton;
	protected SelectionManager selectionManager;
	int portNumber;
	ServerSocketChannel serverSocketChannel;	
	String serverId;
	protected NIOManager observableNIOManager;
	
	//InetAddress localHost;
	public AnNIOSimplexBufferServerInpuPorttDriver (SelectionManager theSelectingRunnable, String thePortId) {
		selectionManager = theSelectingRunnable;
		portNumber = Integer.parseInt(thePortId);
		observableNIOManager = new AnNIOManager(selectionManager);
		AcceptCommandFactorySelector.setFactory(new AReadingAcceptCommandFactory());
//		myName = theMyName;	
	}
	protected void registerAsWriteBufferListener(SocketChannel aSocketChannel) {
		observableNIOManager.addWriteBoundedBufferListener(aSocketChannel, this);
	}
	@Override
	public void connect() {
		serverSocketChannel = createSocketChannel();
//		startChannel();
		if (serverSocketChannel != null)
			registerWithSelectionManager();
	}
	void registerWithSelectionManager() {
		observableNIOManager.enableListenableAccepts(serverSocketChannel, this);
//		AcceptCommand acceptRequestResponse = new AnAcceptCommand(selectionManager, serverSocketChannel, this);
//		selectionManager.putRequestResponse(acceptRequestResponse);	
//		Tracer.info(this, "Waking up selector to process newly queued accept request");
//		selectionManager.getSelector().wakeup();
	}
	protected ServerSocketChannel createSocketChannel() {
		try {			
			//localHost = InetAddress.getLocalHost();
			ServerSocketChannel retVal = ServerSocketChannel.open();
//			retVal.configureBlocking(false);
			InetSocketAddress isa = new InetSocketAddress(portNumber);
			retVal.socket().bind(isa);
			return retVal;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public void socketChannelAccepted(
			ServerSocketChannel theServerSocketChannel,
			SocketChannel aSocketChannel) {
		observableNIOManager.addCloseListener(aSocketChannel, this);
		observableNIOManager.addReadListener(aSocketChannel, this);
		registerAsWriteBufferListener(aSocketChannel);
//		selectionManager.registerCloseListener(theSocketChannel, this);
//		selectionManager.registerReadListener(theSocketChannel, this);
//		socketChannelToClientProxy.put(theSocketChannel, new AClientProxy(this));
			
	}

	@Override
	public void connected(SocketChannel theSocketChannel) {
		selectionManager.registerReadListener(theSocketChannel, this);
	}
		
	@Override
	public void notConnected(
			SocketChannel theSocketChannel, Exception e) {
		e.printStackTrace(); // this should not really happen
	}
	

	@Override
	public void closed(SocketChannel aSocketChannel,
			Exception aReadException) {
		skeleton.disconnected(skeleton.getClientName(aSocketChannel), aReadException instanceof EOFException, aReadException.getMessage(), null);

	}

	@Override
	public void socketChannelRead(SocketChannel theSocketChannel,
			ByteBuffer theMessage, int aLength) {
		skeleton.messageReceived(theSocketChannel, theMessage);

	}
	
	@Override
	public void setSkeleton(SimplexServerInputPortSkeleton<ServerSocketChannel, SocketChannel> theSkeleton) {
		skeleton = theSkeleton;
		
	}
	@Override
	public void disconnect(SocketChannel theChannel) {
		try {
			theChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void disconnect() {
		try {
			serverSocketChannel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void writeBufferIsEmpty(SocketChannel aSocketChannel) {
		observableNIOManager.enableReads(aSocketChannel);
		
	}

	
}
