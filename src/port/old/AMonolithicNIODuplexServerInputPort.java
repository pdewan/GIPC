package port.old;

import inputport.datacomm.duplex.NoMessageReceivedByResponderException;
import inputport.datacomm.duplex.SendToUnkonwnRemoteNameException;
import inputport.nio.manager.AWriteCommand;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.WriteCommand;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import util.trace.Tracer;







public class AMonolithicNIODuplexServerInputPort extends AMonolithicNIOServerInputPort implements MonolithicNIODuplexServerInputPort {
	//String currentSender = null;
	protected String lastSender = null;
//  causes Java to break	
	protected Map<String, SocketChannel> clientNameToSocketChannel = new HashMap();


	public AMonolithicNIODuplexServerInputPort(SelectionManager theSelectingRunnable,
			String thePortId, String theMyName) {
		super(theSelectingRunnable, thePortId, theMyName);
	}
	@Override
	protected void associate (SocketChannel theSocketChannel, String theClientName) {
		super.associate(theSocketChannel, theClientName);
		clientNameToSocketChannel.put(theClientName, theSocketChannel);
	}
	
//	@Override
//	public String[] getConnectionEndPoints() {
	public Set<String> getConnections() {
		Set<String> keySet = clientNameToSocketChannel.keySet();
		Set<String> retVal = new HashSet();
		for (String name: keySet) {
			retVal.add(name);			
		}
		return retVal;
//		return clientNameToSocketChannel.keySet();

//		Collection<ClientProxy> clientProxies = socketChannelToClientProxy.values();
//		int numClients = clientProxies.size();
////		String[] retVal = new String[numClients];
//		List<String> retVal = new ArrayList();
//		int i = 0;
//		for (ClientProxy clientProxy: clientProxies) {
////			retVal[i] = clientProxy.getClientName();
////			i++;
//			retVal.add(clientProxy.getClientName());
//		}
//		
//		return retVal;
	}
	SocketChannel getSocketChannel(String clientName) {
		return  clientNameToSocketChannel.get(clientName);
//		SocketChannel retVal = clientNameToSocketChannel.get(clientName);
//		if (retVal != null) return retVal;
//		for (SocketChannel socketChannel:socketChannelToClientName.keySet()) {
//			if (socketChannelToClientName.get(socketChannel).equals(clientName))  {
//				clientNameToSocketChannel.put(clientName, socketChannel);
//				return socketChannel;
//			}
//		}
//		return null;
		
	}
//	SocketChannel getSocketChannel(String clientName) {
//		SocketChannel retVal = clientNameToSocketChannel.get(clientName);
//		if (retVal != null) return retVal;
//		for (SocketChannel socketChannel:socketChannelToClientProxy.keySet()) {
//			ClientProxy clientProxy = socketChannelToClientProxy.get(socketChannel);
//			if (clientProxy.getClientName().equals(clientName))  {
//				clientNameToSocketChannel.put(clientName, socketChannel);
//				return socketChannel;
//			}
//		}
//		return null;
//		
//	}
	@Override
	public void reply(ByteBuffer message) {
		if (getLastSender() == null) throw new NoMessageReceivedByResponderException();
		Tracer.info(this, "Replying:" + message);
		send(getLastSender(), message);
	}
	
//	@Override
//	public void notifyPortReceive (String remoteEnd, ByteBuffer message) {
//		currentSender = remoteEnd;
//		super.notifyPortReceive(remoteEnd, message);
//		currentSender = null;
//	}

//	@Override
//	public void reply(ByteBuffer message) {
//		if (currentSender == null)
//			// need some error message here
//			return;
//		send (currentSender, message);
//	}
//
	@Override
	public String getLastSender() {
		return lastSender;
	}
	@Override
	public void setLastSender(String newVal) {
		lastSender = newVal;
	}
	@Override
	public void send(String remoteName, ByteBuffer message) {
		SocketChannel socketChannel = getSocketChannel(remoteName);
		if (socketChannel == null) throw new SendToUnkonwnRemoteNameException();			
		WriteCommand bufferedWrite = new AWriteCommand(selectingRunnable, socketChannel, message);
		selectingRunnable.putBufferedWrite(bufferedWrite);
		// why do we need the Id? It is not uniform with other sends
		bufferedWrite.getId();
	}
	@Override
	public void written(SocketChannel socketChannel,
			ByteBuffer theWriteBuffer, int sendId) {
		notifyPortSend(null, theWriteBuffer, sendId);
		
	}
	@Override
	public void closed(SocketChannel theSocketChannel,
			Exception theReadException) {
//		ClientProxy clientProxy = socketChannelToClientProxy.get(theSocketChannel);
//		clientNameToSocketChannel.remove(clientProxy.getClientName());
		clientNameToSocketChannel.remove(socketChannelToClientName.get(theSocketChannel));
		super.closed(theSocketChannel, theReadException);		
	}
	@Override
	public void disconnect() {
		super.disconnect();
		clientNameToSocketChannel.clear();
	}
	@Override
	public void notifyPortReceive (String remoteEnd, ByteBuffer message, int length) {
		lastSender = remoteEnd;
		super.notifyPortReceive(remoteEnd, message, length);
	}
//
//	@Override
//	public void sendAll(ByteBuffer message) {
//		String[] peerNames = getPeerNames();
//		for (int i = 0;i < peerNames.length;  i++)
//			send(peerNames[i],  message);
//		
//	}
//
//	@Override
//	public void sendOthers(ByteBuffer message) {
//		String[] peerNames = getPeerNames();
//		for (int i = 0;i < peerNames.length;  i++) {
//			
//			send(peerNames[i],  message);
//		}
//		
//	}
//
//	@Override
//	public void addPeerConnectListener(ConnectListener portConnectListener) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void addPeerDisconnectListener(DisconnectListener portCloseListener) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void notifyPeerConnect(String remoteEnd) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void notifyPeerDisconnect(String remoteEnd, boolean eof,
//			String closeReason) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void removePeerConnectListener(ConnectListener portConnectListener) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void removePeerDisconnectListener(
//			DisconnectListener portCloseListener) {
//		// TODO Auto-generated method stub
//		
//	}
	public static void main (String[] args) {
		MonolithicDuplexInputPortFactory inputPortFactory = new AMonolithicNIODuplexInputPortFactory();
//		DuplexInputPortSelector.setInputPortFactory(inputPortFactory);
		MonolithicDuplexServerInputPort serverInputPort = inputPortFactory.createDuplexServerInputPort("9090", "test server");
		serverInputPort.connect();
		MonolithicPrintingReplyingReceiveListener echoingReceiveListener = new MonolithicPrintingReplyingReceiveListener(serverInputPort);
		serverInputPort.addConnectionListener(echoingReceiveListener);
		serverInputPort.addDisconnectListener(echoingReceiveListener);
		serverInputPort.addReceiveListener(echoingReceiveListener);		
	}

	@Override
	public void send(ByteBuffer message) {
		reply(message);
		
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void reply(String aSource, ByteBuffer aMessage) {
		// TODO Auto-generated method stub
		
	}

	

	

}
