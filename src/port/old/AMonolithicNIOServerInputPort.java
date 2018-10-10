package port.old;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.AcceptCommand;
import inputport.nio.manager.commands.classes.AnAcceptCommand;




public class AMonolithicNIOServerInputPort extends AConnectionSendReceiptNotifier implements MonolithicNIOServerInputPort  {
	protected SelectionManager selectingRunnable;
	int portNumber;
	ServerSocketChannel serverSocketChannel;	
	String serverId;
//	List<SocketChannel> connectedSocketChannels = new ArrayList();
//	boolean isRelayer;
	String myName;
//	protected Map<SocketChannel, ClientProxy> socketChannelToClientProxy = new Hashtable();
	protected Map<SocketChannel, String> socketChannelToClientName = new HashMap();
//	protected Map<String, SocketChannel> clientNameToSocketChannel = new HashMap();



	//InetAddress localHost;
	public AMonolithicNIOServerInputPort (SelectionManager theSelectingRunnable, String thePortId, String theMyName) {
		selectingRunnable = theSelectingRunnable;
		portNumber = Integer.parseInt(thePortId);
		myName = theMyName;	
	}
	@Override
	public void connect() {
		serverSocketChannel = createSocketChannel();
		registerWithSelectionRunnable();
	}
	void registerWithSelectionRunnable() {		
		AcceptCommand acceptRequestResponse = new AnAcceptCommand(selectingRunnable, serverSocketChannel);
		selectingRunnable.putRequestResponse(acceptRequestResponse);	
		selectingRunnable.getSelector().wakeup();
		//selectingRunnable.registerReadListener(this);
	}
	ServerSocketChannel createSocketChannel() {
		try {			
			//localHost = InetAddress.getLocalHost();
			ServerSocketChannel retVal = ServerSocketChannel.open();
			retVal.configureBlocking(false);
			InetSocketAddress isa = new InetSocketAddress(portNumber);
			retVal.socket().bind(isa);
			return retVal;
//			serverSocketChannel = ServerSocketChannel.open();
//			serverSocketChannel.configureBlocking(false);
//			InetSocketAddress isa = new InetSocketAddress(portNumber);
//			serverSocketChannel.socket().bind(isa);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
//	@Override
//	public void send(ByteBuffer message) {
//		
//	}
	
	@Override
	public void socketChannelAccepted(
			ServerSocketChannel theServerSocketChannel,
			SocketChannel theSocketChannel) {
		selectingRunnable.registerCloseListener(theSocketChannel, this);
		selectingRunnable.registerReadListener(theSocketChannel, this);
//		socketChannelToClientProxy.put(theSocketChannel, new AClientProxy(this));
			
	}
//	@Override
//	public void socketChannelRead(SocketChannel theSocketChannel,
//			ByteBuffer message) {
//		String clientSocketAddress = theSocketChannel.socket().getRemoteSocketAddress().toString();
//		InetAddress host = theSocketChannel.socket().getInetAddress();
//		String clientName = socketAddressToClientName.get(clientSocketAddress);		
//		if (clientName == null) {
//			byte[] stringBytes = new byte[message.remaining()];
//			message.get(stringBytes);
//			clientName = new String(stringBytes);
//			socketAddressToClientName.put(clientSocketAddress, clientName);
//			notifyPortConnectListeners(host, clientName);
//		} else {
//			notifyPortReceiveListeners(host, clientName, message);			
//		}		
//	}
//	@Override
//	public void socketChannelRead(SocketChannel theSocketChannel,
//			ByteBuffer message) {
//		String clientSocketAddress = theSocketChannel.socket().getRemoteSocketAddress().toString();
//		InetAddress host = theSocketChannel.socket().getInetAddress();
//		String clientName = socketAddressToClientName.get(clientSocketAddress);		
//		if (clientName == null) {
//			byte[] stringBytes = new byte[message.remaining()];
//			message.get(stringBytes);
//			clientName = new String(stringBytes);
//			socketAddressToClientName.put(clientSocketAddress, clientName);
//			notifyPortConnectListeners(clientName);
//		} else {
//			notifyPortReceiveListeners(clientName, message);			
//		}		
//	}
//	@Override
//	public void init(String serverID) {
//		// TODO Auto-generated method stub
//		
//	}
	@Override
	public void connected(SocketChannel theSocketChannel) {
//		socketChannelToClientProxy.put(theSocketChannel, new AClientProxy(this));
		selectingRunnable.registerReadListener(theSocketChannel, this);
		//selectingRunnable.registerReadListener(theSocketChannel, new AClientProxy(this));
		//connectedSocketChannels.add(theSocketChannel);
	}
	@Override
	public void notConnected(
			SocketChannel theSocketChannel, Exception e) {
		e.printStackTrace(); // this should not really happen
	}
	
//	@Override
//	public void remove(SocketChannel socketChannel) {
//		connectedSocketChannels.remove(socketChannel);
//	}
	
	
//	@Override
//	public String[] getPeerNames() {
//		Collection<ClientProxy> clientProxies = socketChannelToClientProxy.values();
//		int numClients = clientProxies.size();
//		String[] retVal = new String[numClients];
//		int i = 0;
//		for (ClientProxy clientProxy: clientProxies) {
//			retVal[i] = clientProxy.getClientName();
//			i++;
//		}
//		
//		return retVal;
//	}
	@Override
	public void closed(SocketChannel theSocketChannel,
			Exception theReadException) {
		notifyDisconnect(socketChannelToClientName.get(theSocketChannel), theReadException instanceof EOFException, theReadException.getMessage(), null);
		socketChannelToClientName.remove(theSocketChannel);
//
//		socketChannelToClientProxy.get(theSocketChannel).closed(theSocketChannel, theReadException);
//		socketChannelToClientProxy.remove(theSocketChannel);		
	}
	protected void associate (SocketChannel theSocketChannel, String theClientName) {
		socketChannelToClientName.put(theSocketChannel, theClientName);
//		clientNameToSocketChannel.put(theClientName, theSocketChannel);
	}
	@Override
	public void socketChannelRead(SocketChannel theSocketChannel,
			ByteBuffer theMessage, int aLength) {
//		socketChannelToClientProxy.get(theSocketChannel).socketChannelRead(theSocketChannel, theMessage, length);
		String clientName = socketChannelToClientName.get(theSocketChannel);
		if (clientName == null) {
//			byte[] stringBytes = new byte[message.limit()];
			byte[] stringBytes = new byte[theMessage.limit() - theMessage.position()];
			//message.rewind();
//			for (int i = 0; i < message.position(); i++)
//				stringBytes[i] = message.get(i);
			theMessage.get(stringBytes);
			//message.get(stringBytes, 0, message.position() -1 );
			clientName = new String(stringBytes);
//			socketChannelToClientName.put(theSocketChannel, clientName);
			associate(theSocketChannel, clientName);
			notifyConnect(clientName, null);
		} else {
			notifyPortReceive(clientName, theMessage, theMessage.limit() - theMessage.position());			
		}		
	}
	@Override
	public void disconnect() {
		for (SocketChannel socketChannel: socketChannelToClientName.keySet()) {
			try {
				socketChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		socketChannelToClientName.clear();
//		socketChannelToClientProxy.clear();
		//Collection<ClientProxy> clientProxies = socketChannelToClientProxy.values();		
	}
	public static void main (String[] args) {
		MonolithicInputPortFactory inputPortFactory = new AMonolithicNIOInputPortFactory();
//		InputPortSelector.setInputPortFactory(inputPortFactory);
		MonolithicServerInputPort serverInputPort = inputPortFactory.createServerInputPort("9090", "test server");
		serverInputPort.connect();
		MonolithicPrintingReceiveListener echoingReceiveListener = new MonolithicPrintingReceiveListener(null);
		serverInputPort.addConnectionListener(echoingReceiveListener);
		serverInputPort.addConnectionListener(echoingReceiveListener);

		serverInputPort.addReceiveListener(echoingReceiveListener);		
	}
	
}
