package oldgroupip;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.DisconnectListener;
import inputport.datacomm.duplex.NoMessageReceivedByResponderException;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.Set;

import port.old.AMonolithicNIODuplexInputPortFactory;
import port.old.ByteBufferReceiveListener;
import port.old.MonolithicDuplexServerInputPort;




public class AGroupServerInputPort implements GroupServerInputPort {
//	protected String lastSender = null;
	MonolithicDuplexServerInputPort delegatePort;
	public void addSendListener(ByteBufferSendListener portSendListener) {
		delegatePort.addSendListener(portSendListener);
	}

	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		delegatePort.notifyPortSend(null, message, sendId);
	}

	public void removeSendListener(ByteBufferSendListener portSendListener) {
		delegatePort.removeSendListener(portSendListener);
	}

//	public void written(SocketChannel socketChannel,
//			ByteBuffer theWriteBuffer, int sendId) {
//		delegatePort
//				.written(socketChannel, theWriteBuffer, sendId);
//	}

	// no need for factories as we will have only one implementation
	public AGroupServerInputPort(String theServerId, String theServerName) {
		delegatePort = (new AMonolithicNIODuplexInputPortFactory()).createDuplexServerInputPort(theServerId, theServerName);
//		delegatePort = MonolithicDuplexInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
//		delegatePort.addReceiveListener(this);
}

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
	public void reply(ByteBuffer message) {
//		if (getLastSender() == null) throw new NoMessageReceivedByResponderException();
//		Message.info("Replying:" + message);
//		return send(getLastSender(), message);
		delegatePort.reply(message);
	}
	@Override
	public void send(ByteBuffer message) {
		 reply(message);
	}

	@Override
	public void sendAll(ByteBuffer message) {
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
	public void send(Set<String> clientNames, ByteBuffer message) {
		ByteBuffer messageCopy = null;
		int position = 0;
		int limit = 0;
		int retVal = -1;
		for (String clientName:clientNames) {
			if (messageCopy == null) {
				messageCopy = message;
				position = message.position();
				limit = message.limit();				
			} else {
				messageCopy = ByteBuffer.wrap(message.array(), position, limit);
			}
			send(clientName, messageCopy);			
		}
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
	public void sendOthers(ByteBuffer message) {
		if (getLastSender() == null) throw new NoMessageReceivedByResponderException();
		Set<String> peerNames = getConnections();
		peerNames.remove(getLastSender());
		send(peerNames, message);
	}

//	public void addConnectionListener(ConnectionListener portCloseListener) {
//	delegatePort.addConnectionListener(portCloseListener);
//	}

public void addConnectionListener(ConnectionListener portConnectListener) {
	delegatePort.addConnectionListener(portConnectListener);
}

public void addDisconnectListener(DisconnectListener portCloseListener) {
	delegatePort.addDisconnectListener(portCloseListener);
}

public void addReceiveListener(ByteBufferReceiveListener portReceiveListener) {
	delegatePort.addReceiveListener(portReceiveListener);
}

public void disconnect() {
	delegatePort.disconnect();
}

public Set<String> getConnections() {
	return delegatePort.getConnections();
}

public void connect() {
	delegatePort.connect();
}

public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
	delegatePort.notifyConnect(remoteEnd, aConnectionType);
}

@Override
public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
	delegatePort.notifyConnectFailure(remoteEnd, message, null);
	
}

public void notifyDisconnect(String remoteEnd, boolean eof, String closeReason, ConnectionType aConnectionType) {
	delegatePort.notifyDisconnect(remoteEnd, eof, closeReason, null);
}

public void notifyPortReceive(String remoteEnd, ByteBuffer message, int length) {
	delegatePort.notifyPortReceive(remoteEnd, message, length);
}

//public void removeConnectionListener(ConnectionListener portCloseListener) {
//	delegatePort.removeConnectionListener(portCloseListener);
//}

public void removeConnectionListener(ConnectionListener portConnectListener) {
	delegatePort.removeConnectionListener(portConnectListener);
}

public void removeDisconnectListener(DisconnectListener portCloseListener) {
	delegatePort.removeDisconnectListener(portCloseListener);
}

public void removeReceiveListener(ByteBufferReceiveListener portReceiveListener) {
	delegatePort.removeReceiveListener(portReceiveListener);
}

public void send(String remoteName, ByteBuffer message) {
	delegatePort.send(remoteName, message);
}
@Override
public String getLastSender() {
	return delegatePort.getLastSender();
}
//keep track of the last sender
//@Override
//public void messageReceived(String remoteClientName, ByteBuffer message, int length) {
//	lastSender = remoteClientName;	
//}
@Override
public void setLastSender(String newVal) {
	delegatePort.setLastSender(newVal);
	
}
public static void main (String[] args) {
//	GroupServerInputPort serverInputPort = new AGroupServerInputPort("9090", "test server");
//	serverInputPort.connect();
//	PrintingBroadcastingReceiverListener echoingReceiveListener = new PrintingBroadcastingReceiverListener(serverInputPort);
//	serverInputPort.addConnectListener(echoingReceiveListener);
//	serverInputPort.addDisconnectListener(echoingReceiveListener);
//	serverInputPort.addReceiveListener(echoingReceiveListener);	
//	serverInputPort.addSendListener(echoingReceiveListener);
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
