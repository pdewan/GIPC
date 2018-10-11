package inputport.datacomm.simplex.buffer.nio;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import inputport.ConnectionType;
import inputport.datacomm.simplex.buffer.SimplexClientInputPortSkeleton;
import inputport.nio.manager.AnNIOManager;
import inputport.nio.manager.NIOManager;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.factories.selectors.ConnectCommandFactorySelector;
import util.trace.Tracer;


public class AnNIOSimplexBufferClientInputPortDriver  implements NIOSimplexClientInputDriver {
	InetAddress host;
	int port;
	protected SelectionManager selectionManager;
	protected String serverName;
	protected SocketChannel socketChannel;
	protected NIOManager observableNIOManager;

	SimplexClientInputPortSkeleton<SocketChannel> clientInputPortSkeleton;
	public AnNIOSimplexBufferClientInputPortDriver(SelectionManager theSelectingRunnable, String theRemoteHostName, String theRemotePort, String aServerName) {
		try {
			host = InetAddress.getByName(theRemoteHostName);
			port = Integer.parseInt(theRemotePort);
			selectionManager = theSelectingRunnable;
			serverName = aServerName;
			observableNIOManager = new AnNIOManager(selectionManager);
			ConnectCommandFactorySelector.setFactory(new AWritingConnectCommandFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setSkeleton(SimplexClientInputPortSkeleton<SocketChannel> theSkeleton) {
		clientInputPortSkeleton = theSkeleton;
	}
	@Override
	public SimplexClientInputPortSkeleton getSkeleton() {
		return clientInputPortSkeleton;
	}
	@Override
	public void connect() {
		socketChannel = createSocketChannel();
		registerWithSelectionRunnable();
	}
	void registerWithSelectionRunnable() {
		observableNIOManager.addCloseListener(socketChannel, this);
		observableNIOManager.connect(socketChannel, host, port, this);
		
//		selectionManager.registerCloseListener(socketChannel, this);
//		ConnectCommand connectRequestResponse = 
//			new AConnectCommand(selectionManager, socketChannel, host, port, this);
//		selectionManager.putRequestResponse(connectRequestResponse);
//		Tracer.info(this, "Waking up selector to process newly queued connect request");
//		selectionManager.getSelector().wakeup();
//		Tracer.info(this, "ClientInputPort connected with channel:" + socketChannel);
	}
	SocketChannel createSocketChannel() {
		try {
			SocketChannel retVal = SocketChannel.open();
//			retVal.configureBlocking(false);
			return retVal;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void send(String destination, ByteBuffer message) {
		Tracer.info(this, "Sending messsage: " + message + " to " + socketChannel);
		observableNIOManager.write(socketChannel, message, this);
//		WriteCommand bufferedWrite = new AWriteCommand(selectionManager, socketChannel, message, this, this);
//		selectionManager.putBufferedWrite(bufferedWrite);
		
	}

	@Override
	public void connected(SocketChannel theSocketChannel) {

		clientInputPortSkeleton.connected(serverName, ConnectionType.TO_SERVER);

		
	}
	@Override
	public void notConnected(
			SocketChannel theSocketChannel, Exception e) {
		clientInputPortSkeleton.notConnected (serverName, e.getMessage(), null);		
	}
	@Override
	public void disconnect() {
		try {
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void closed(SocketChannel theSocketChannel,
			Exception anException) {
		clientInputPortSkeleton.disconnected(serverName, (anException instanceof EOFException), anException.getMessage(), null);		
	}
	@Override
	public void written(SocketChannel socketChannel,
			ByteBuffer theWriteBuffer, int sendId) {
		clientInputPortSkeleton.messageSent(serverName, theWriteBuffer, sendId);		
	}

	
	
	
	
}
