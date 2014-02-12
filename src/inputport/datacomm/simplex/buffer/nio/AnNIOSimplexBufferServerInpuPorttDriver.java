package inputport.datacomm.simplex.buffer.nio;
import inputport.datacomm.simplex.buffer.SimplexServerInputPortSkeleton;

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
	protected ObservableNIOManager observableNIOManager;
	
	//InetAddress localHost;
	public AnNIOSimplexBufferServerInpuPorttDriver (SelectionManager theSelectingRunnable, String thePortId) {
		selectionManager = theSelectingRunnable;
		portNumber = Integer.parseInt(thePortId);
		observableNIOManager = new AnObservableNIOManager(selectionManager);
//		myName = theMyName;	
	}
	@Override
	public void connect() {
		serverSocketChannel = createSocketChannel();
//		startChannel();
		if (serverSocketChannel != null)
			registerWithSelectionManager();
	}
	void registerWithSelectionManager() {
		observableNIOManager.accept(serverSocketChannel, this);
//		AcceptCommand acceptRequestResponse = new AnAcceptCommand(selectionManager, serverSocketChannel, this);
//		selectionManager.putRequestResponse(acceptRequestResponse);	
//		Tracer.info(this, "Waking up selector to process newly queued accept request");
//		selectionManager.getSelector().wakeup();
	}
	ServerSocketChannel createSocketChannel() {
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
			SocketChannel theSocketChannel) {
		observableNIOManager.addCloseListener(theSocketChannel, this);
		observableNIOManager.addReadListener(theSocketChannel, this);
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
			ByteBuffer theMessage) {
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

	
}
