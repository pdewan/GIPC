package oldtypedip;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.NoMessageReceivedByResponderException;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;




public class AnOldGroupServerInputPort<MessageType>  implements GroupServerInputPort<MessageType> {
//	protected String lastSender = null;
//	MonolithicDuplexServerInputPort duplexServerInputPort;
	DuplexServerInputPort<MessageType> duplexServerInputPort;
	GroupNamingSender<MessageType> groupNamingSender;
	public AnOldGroupServerInputPort(DuplexServerInputPort<MessageType> aDuplexServerInputPort, 
			GroupNamingSender<MessageType> aGroupNamingSender) {
		groupNamingSender = aGroupNamingSender;
		duplexServerInputPort = aDuplexServerInputPort;
	}	
	public void addSendListener(ByteBufferSendListener portSendListener) {
		duplexServerInputPort.addSendListener(portSendListener);
	}

	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		duplexServerInputPort.notifyPortSend(null, message, sendId);
	}

	public void removeSendListener(ByteBufferSendListener portSendListener) {
		duplexServerInputPort.removeSendListener(portSendListener);
	}

//	public void written(SocketChannel socketChannel,
//			ByteBuffer theWriteBuffer, int sendId) {
//		duplexServerInputPort
//				.written(socketChannel, theWriteBuffer, sendId);
//	}

	// no need for factories as we will have only one implementation

//	
//
//	public AGroupServerInputPort(SelectingRunnable theSelectingRunnable,
//			String thePortId, String theMyName) {
//		super(theSelectingRunnable, thePortId, theMyName);
//	}
//	
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
	public void reply(MessageType message) {
//		if (getLastSender() == null) throw new NoMessageReceivedByResponderException();
//		Message.info("Replying:" + message);
//		return send(getLastSender(), message);
		duplexServerInputPort.reply(message);
	}
	@Override
	public void send(MessageType message) {
		 reply(message);
	}

	@Override
	public void sendAll(MessageType message) {
		Set<String> peerNames = getConnections();
		send(peerNames, message);
		
	}
//	public int sendAll(ByteBuffer message) {
//		String[] peerNames = getConnectionEndPoints();
//		int retVal = -1;
//		for (int i = 0;i < peerNames.length;  i++)
//			retVal = send(peerNames[i],  message);
//		return retVal;
//		
//	}
	@Override
	public void send(Set<String> clientNames, MessageType message) {
		groupNamingSender.send(clientNames, message);
	}
	
	@Override
//	public int sendOthers(ByteBuffer message) {
//		if (lastSender == null) throw new NoMessageReceivedByResponderException();
//		String[] peerNames = getConnectionEndPoints();
//		int retValue = -1;
//		for (int i = 0;i < peerNames.length;  i++) {
//			if (peerNames[i] != null && peerNames[i].equals(lastSender))
//				continue;
//			retValue = send(peerNames[i],  message);
//		}	
//		return retValue;
//	}
	public void sendOthers(MessageType message) {
		if (getSender() == null) throw new NoMessageReceivedByResponderException();
		Set<String> peerNames = getConnections();
		peerNames.remove(getSender());
		send(peerNames, message);
	}

//	public void addConnectionListener(ConnectionListener portCloseListener) {
//	duplexServerInputPort.addConnectionListener(portCloseListener);
//	}

public void addConnectionListener(ConnectionListener portConnectListener) {
	duplexServerInputPort.addConnectionListener(portConnectListener);
}

//public void addDisconnectListener(DisconnectListener portCloseListener) {
//	duplexServerInputPort.addDisconnectListener(portCloseListener);
//}

public void addReceiveListener(ReceiveListener<MessageType> portReceiveListener) {
	duplexServerInputPort.addReceiveListener(portReceiveListener);
}

public void disconnect() {
	duplexServerInputPort.disconnect();
}

public Set<String> getConnections() {
	return duplexServerInputPort.getConnections();
}

public void connect() {
	duplexServerInputPort.connect();
}

public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
	duplexServerInputPort.notifyConnect(remoteEnd, null);
}

@Override
public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
	duplexServerInputPort.notifyConnectFailure(remoteEnd, message, null);
	
}

public void notifyDisconnect(String remoteEnd, boolean eof, String closeReason, ConnectionType aConnectionType) {
	duplexServerInputPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
}

public void notifyPortReceive(String remoteEnd, MessageType message) {
	duplexServerInputPort.notifyPortReceive(remoteEnd, message);
}

//public void removeConnectionListener(ConnectionListener portCloseListener) {
//	duplexServerInputPort.removeConnectionListener(portCloseListener);
//}

public void removeConnectionListener(ConnectionListener portConnectListener) {
	duplexServerInputPort.removeConnectionListener(portConnectListener);
}

//public void removeDisconnectListener(DisconnectListener portCloseListener) {
//	duplexServerInputPort.removeDisconnectListener(portCloseListener);
//}


public void send(String remoteName, MessageType message) {
	duplexServerInputPort.send(remoteName, message);
}
@Override
public String getSender() {
	return duplexServerInputPort.getSender();
}
//keep track of the last sender
//@Override
//public void messageReceived(String remoteClientName, ByteBuffer message, int length) {
//	lastSender = remoteClientName;	
//}
@Override
public void setSender(String newVal) {
	duplexServerInputPort.setSender(newVal);
	
}
@Override
public String getLocalName() {
	return duplexServerInputPort.getLocalName();
}
@Override
public String getServerId() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void removeReceiveListener(
		ReceiveListener<MessageType> portReceiveListener) {
	// TODO Auto-generated method stub
	
}
@Override
public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void setReceiveTrapper(ReceiveTrapper<MessageType, MessageType> newVal) {
	// TODO Auto-generated method stub
	
}
@Override
public SendTrapper<MessageType, MessageType> getSendTrapper() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
	// TODO Auto-generated method stub
	
}
@Override
public GroupSendTrapper<MessageType, MessageType> getGroupSendTrapper() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public GroupToUniSendTrapper<MessageType, MessageType> getGroupToUniSendTrapper() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void setGroupSendTrapper(
		GroupSendTrapper<MessageType, MessageType> groupSendTrapper) {
	// TODO Auto-generated method stub
	
}
@Override
public void setGroupToUniSendTrapper(
		GroupToUniSendTrapper<MessageType, MessageType> groupToUniSendTrapper) {
	// TODO Auto-generated method stub
	
}
@Override
public boolean isConnected(String remoteName) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public List<ReceiveListener<MessageType>> getReceiveListeners() {
	// TODO Auto-generated method stub
	return duplexServerInputPort.getReceiveListeners();
}
@Override
public String getLogicalRemoteEndPoint() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public String getPhysicalRemoteEndPoint() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void setLogicalRemoteEndPoint(String newVal) {
	// TODO Auto-generated method stub
	
}
@Override
public void setPhysicalRemoteEndPoint(String newVal) {
	// TODO Auto-generated method stub
	
}
@Override
public void reply(String aSource, MessageType aMessage) {
	// TODO Auto-generated method stub
	
}


//@Override
//public ReceiveNotifier<Object> getReceiveTrapper() {
//	// TODO Auto-generated method stub
//	return null;
//}
//@Override
//public void setReceiveTrapper(ReceiveNotifier<Object> newVal) {
//	// TODO Auto-generated method stub
//	
//}
//@Override
//public UniNamingSender<Object> getSendTrapper() {
//	// TODO Auto-generated method stub
//	return null;
//}
//@Override
//public void setSendTrapper(UniNamingSender<Object> newVal) {
//	// TODO Auto-generated method stub
//	
//}




	



}
