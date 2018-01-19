package port.old;

import inputport.datacomm.SendToUnconnectedPortException;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.ConnectCommand;
import inputport.nio.manager.commands.WriteCommand;
import inputport.nio.manager.commands.classes.AConnectCommand;
import inputport.nio.manager.commands.classes.AWriteCommand;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Set;

import extraip.ASendConnectionListener;

public class AMonolithicNIOClientInputPort extends AConnectionSendReceiptNotifier implements MonolithicNIOClientInputPort {
	InetAddress host;
	int port;
	protected SelectionManager selectingRunnable;
	String myName;
	protected String serverName;
	SocketChannel socketChannel;
	boolean connected = false;
	String[] remoteNames;
	public AMonolithicNIOClientInputPort(SelectionManager theSelectingRunnable, String theRemoteHostName, String theRemotePort, String theMyName) {
		try {
			host = InetAddress.getByName(theRemoteHostName);
			port = Integer.parseInt(theRemotePort);
			selectingRunnable = theSelectingRunnable;
			myName = theMyName;
			serverName = theRemoteHostName+":"+theRemotePort;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void connect() {
		connected = true;
		socketChannel = createSocketChannel();
		sendClientName();
		registerWithSelectionRunnable();
//		connected = true;
	}
	void sendClientName() {
		ByteBuffer message =  ByteBuffer.wrap (myName.getBytes());
		send(message);
//		BufferedWrite bufferedWrite = new ABufferedWrite(selectingRunnable, socketChannel, message, null);
//		selectingRunnable.putBufferedWrite(bufferedWrite);
	}
	
	void registerWithSelectionRunnable() {
		selectingRunnable.registerCloseListener(socketChannel, this);
		ConnectCommand connectRequestResponse = 
			new AConnectCommand(selectingRunnable, socketChannel, host, port);
		selectingRunnable.putRequestResponse(connectRequestResponse);
		selectingRunnable.getSelector().wakeup();
		//selectingRunnable.registerReadListener(this);
	}
	SocketChannel createSocketChannel() {
		try {
			SocketChannel retVal = SocketChannel.open();
			retVal.configureBlocking(false);
			return retVal;
//			socketChannel = SocketChannel.open();
//			socketChannel.configureBlocking(false);
			
//			socketChannel = SocketChannel.open();
//			socketChannel.configureBlocking(false);	
			//selectingRunnable.registerReadListener(socketChannel, this);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void send(ByteBuffer message) {
		send (serverName, message);
//		if (!connected) throw new SendToUnconnectedPortException();
//		BufferedWrite bufferedWrite = new ABufferedWrite(selectingRunnable, socketChannel, message, this);
//		selectingRunnable.putBufferedWrite(bufferedWrite);
////		return bufferedWrite.getId();
		
	}
	@Override
	public void send(String theRemoteName, ByteBuffer message) {
		if (!connected) throw new SendToUnconnectedPortException(theRemoteName);
		WriteCommand bufferedWrite = new AWriteCommand(selectingRunnable, socketChannel, message);
		selectingRunnable.putBufferedWrite(bufferedWrite);
//		return bufferedWrite.getId();
		
	}
//	void sendMyNameMessage() {
//		ByteBuffer myNameMessage = ByteBuffer.wrap(myName.getBytes());
//		BufferedWrite bufferedWrite = new ABufferedWrite(selectingRunnable, socketChannel, myNameMessage, null);
//		selectingRunnable.putBufferedWrite(bufferedWrite);	
//	}
	@Override
	public void connected(SocketChannel theSocketChannel) {
		notifyConnect(serverName, null);
		
	}
	@Override
	public void notConnected(
			SocketChannel theSocketChannel, Exception e) {
		notifyConnectFailure(serverName, e.getMessage(), null);
		
	}

	@Override
	public void disconnect() {
		try {
			socketChannel.close();
			connected = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
//	@Override
//	public String[] getPeerNames() {
//		String[] retVal = {serverName};
//		return retVal;
//	}
//	
	@Override
	public void closed(SocketChannel theSocketChannel,
			Exception theReadException) {
		notifyDisconnect(serverName, (theReadException instanceof EOFException), theReadException.getMessage(), null);		
	}
	@Override
	public void written(SocketChannel socketChannel,
			ByteBuffer theWriteBuffer, int sendId) {
		notifyPortSend(null, theWriteBuffer, sendId);		
	}
	public static void main (String[] args) {
		MonolithicInputPortFactory inputPortFactory = new AMonolithicNIOInputPortFactory();
//		InputPortSelector.setInputPortFactory(inputPortFactory);
		MonolithicClientInputPort clientInputPort = inputPortFactory.createClientInputPort("localhost", "9090", "test client");
		ASendConnectionListener echoingConnectionListener = new ASendConnectionListener(null);
		clientInputPort.addConnectionListener(echoingConnectionListener);
		clientInputPort.addDisconnectListener(echoingConnectionListener);
		clientInputPort.connect();
		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		byte[] padding = new byte[10];
//		message.position(message.limit());
//		message.put(padding);
//		message.flip();
		System.out.println("String with padding:" + new String(message.array()));		
		clientInputPort.send(message);
	}
	@Override
	public String getLogicalRemoteEndPoint() {
		// TODO Auto-generated method stub
		return serverName;
	}
	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<String> getConnections() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		// TODO Auto-generated method stub
		return getLogicalRemoteEndPoint();
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {

		
	}
	@Override
	public boolean isConnected(String remoteName) {
		
		return false;
	}

	
}
