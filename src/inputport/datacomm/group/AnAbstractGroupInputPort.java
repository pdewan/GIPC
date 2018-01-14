package inputport.datacomm.group;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.NoMessageReceivedByResponderException;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;





public abstract class AnAbstractGroupInputPort<MessageType>  implements 
	GroupInputPort<MessageType>, ReceiveListener<MessageType> {
	String lastSender;
	String lastSenderOfNonReplyMessage;
//	protected String lastSender = null;
//	MonolithicDuplexServerInputPort duplexServerInputPort;
	protected GroupSendTrapper<MessageType,  MessageType> groupSendTrapper;
	protected ReceiveTrapper<MessageType, MessageType> receiveTrapper;
	protected GroupToUniSendTrapper<MessageType, MessageType> groupToUniSendTrapper;	

	protected DuplexInputPort<MessageType> duplexInputPort;
	protected ReceiveRegistrarAndNotifier receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier();
//	protected GroupNamingSender<MessageType> serializingGroupForwarder;
//	protected GroupNamingSender<MessageType> multipleSendGroupForwarder;
//	protected UniNamingSender<Object> unOptimizedObjectForwarder;
//	protected GroupNamingSender<Object> optimizedObjectForwarder;

//	GroupNamingSender<MessageType> groupNamingSender;
	public AnAbstractGroupInputPort(DuplexInputPort<MessageType> aDuplexServerInputPort) {
//		groupNamingSender = aGroupNamingSender;
		duplexInputPort = aDuplexServerInputPort;
		duplexInputPort.addReceiveListener(this);
	}	
	public void addSendListener(ByteBufferSendListener portSendListener) {
		duplexInputPort.addSendListener(portSendListener);
	}

	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		duplexInputPort.notifyPortSend(aRemoteEnd, message, sendId);
	}

	public void removeSendListener(ByteBufferSendListener portSendListener) {
		duplexInputPort.removeSendListener(portSendListener);
	}
	@Override
	public String getLocalName() {
		return duplexInputPort.getLocalName();
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
//		duplexInputPort.reply(message);
		String lastSender = getSender();
		if (lastSender == null)	return;
		send(lastSender, message);
		
	}
	@Override
	public void reply(String aRemoteEnd, MessageType aMessage) {
		send(aRemoteEnd, aMessage);
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
	@Override
	public Set<String> getConnections() {
		return duplexInputPort.getConnections();
	}

	
//	public int sendAll(ByteBuffer message) {
//		String[] peerNames = getConnectionEndPoints();
//		int retVal = -1;
//		for (int i = 0;i < peerNames.length;  i++)
//			retVal = send(peerNames[i],  message);
//		return retVal;
//		
//	}
//	@Override
//	public void send(Set<String> clientNames, MessageType message) {
//		groupNamingSender.send(clientNames, message);
//	}
	
	
	
//	@Override
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

protected Set<String> toOthers (Set<String> all) {
	if (getSender() == null) throw new NoMessageReceivedByResponderException();
	Set retVal = new HashSet(all);
	retVal.remove(getSender());
	return retVal;
}
	
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
	duplexInputPort.addConnectionListener(portConnectListener);
}

//public void addDisconnectListener(DisconnectListener portCloseListener) {
//	duplexServerInputPort.addDisconnectListener(portCloseListener);
//}

public void addReceiveListener(ReceiveListener<MessageType> portReceiveListener) {
//	duplexServerInputPort.addReceiveListener(portReceiveListener);
	receiveRegistrarAndNotifier.addReceiveListener(portReceiveListener);
}

@Override
public List<ReceiveListener<MessageType>> getReceiveListeners() {
	return receiveRegistrarAndNotifier.getReceiveListeners();
}

public void disconnect() {
	duplexInputPort.disconnect();
}

//public Set<String> getRemoteEndPoints() {
//	return duplexInputPort.getRemoteEndPoints();
//}

public void connect() {
	duplexInputPort.connect();
}

public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
	duplexInputPort.notifyConnect(remoteEnd, aConnectionType);
}

@Override
public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
	duplexInputPort.notifyConnectFailure(remoteEnd, message, null);
	
}

public void notifyDisconnect(String remoteEnd, boolean eof, String closeReason, ConnectionType aConnectionType) {
	duplexInputPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
}

@Override
public void notifyPortReceive(String remoteEnd, MessageType message) {
	// used to be duplexServerInputPort.no...
	receiveTrapper.notifyPortReceive(remoteEnd, message);
}

//public void removeConnectionListener(ConnectionListener portCloseListener) {
//	duplexServerInputPort.removeConnectionListener(portCloseListener);
//}

public void removeConnectionListener(ConnectionListener portConnectListener) {
	duplexInputPort.removeConnectionListener(portConnectListener);
}

//public void removeDisconnectListener(DisconnectListener portCloseListener) {
//	duplexServerInputPort.removeDisconnectListener(portCloseListener);
//}

public void removeReceiveListener(ReceiveListener portReceiveListener) {
//	duplexServerInputPort.removeReceiveListener(portReceiveListener);
	receiveRegistrarAndNotifier.removeReceiveListener(portReceiveListener);

}

public void send(String remoteName, MessageType message) {
	Tracer.info(this, "Sending message:" + message + " to "  + remoteName);
	Set<String> names = new HashSet();
	names.add(remoteName);
	send(names, message);
}
public void  messageReceived(String remoteClientName, MessageType message) {
// will set a diferent variable for non reply
	lastSender = remoteClientName;
	receiveTrapper.notifyPortReceive(remoteClientName, message);
//	notifyPortReceive(remoteClientName, message);
}


//@Override
//public String getLastSender() {
//	return duplexInputPort.getLastSender();
//}
//keep track of the last sender
//@Override
//public void messageReceived(String remoteClientName, ByteBuffer message, int length) {
//	lastSender = remoteClientName;	
//}
//@Override
//public void setLastSender(String newVal) {
//	duplexInputPort.setLastSender(newVal);
//	
//}

//@Override
//public String getServerId() {
//	return duplexInputPort.getServerId();
//}


@Override
public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
	return receiveTrapper;
}
@Override
public void setReceiveTrapper(ReceiveTrapper<MessageType, MessageType> newVal) {
	if (newVal.getDestination() == null) {
		newVal.setDestination(receiveTrapper.getDestination());
		Tracer.warning("receive trapper == mull!");
	} else if (newVal.getDestination() == receiveTrapper) { // adding a new one in front of old one
		ConnectiontEventBus.newEvent(new AReplaceConnectionEvent(this, receiveTrapper, newVal, false, false));

	} else {
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, false));
	}
	receiveTrapper = newVal;

//	DistEventsBus.newEvent(new AConnectionEvent(this, receiveTrapper, false));
//	receiveTrapper.setDestination(receiveRegistrarAndNotifier);
	
}
@Override
public SendTrapper<MessageType, MessageType> getSendTrapper() {
	return duplexInputPort.getSendTrapper();
}
// this makes no sense for this port, so just push it down;
@Override
public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
	Tracer.warning("Send trapper being set in duplex port:"  + duplexInputPort + " and not this port:" + this);
	duplexInputPort.setSendTrapper(newVal);
}
@Override
public GroupSendTrapper<MessageType, MessageType> getGroupSendTrapper() {
	return groupSendTrapper;
}
@Override
public void setGroupSendTrapper(
		GroupSendTrapper<MessageType, MessageType> newVal) {
	if (newVal.getDestination() == null) {
		newVal.setDestination(groupSendTrapper.getDestination());
		Tracer.warning("send trapper == mull!");
	} else if (newVal.getDestination() == groupSendTrapper) { // adding a new one in front of old one
		ConnectiontEventBus.newEvent(new AReplaceConnectionEvent(this, groupSendTrapper, newVal, true, false));

	} else {
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
	}
	this.groupSendTrapper = newVal;
//	DistEventsBus.newEvent(new AConnectionEvent(this, newVal, true));
//	groupSendTrapper.setDestination(groupToUniSendTrapper);
}
@Override
public GroupToUniSendTrapper<MessageType, MessageType> getGroupToUniSendTrapper() {
	return groupToUniSendTrapper;
}
@Override
public void setGroupToUniSendTrapper(
		GroupToUniSendTrapper<MessageType, MessageType> groupToUniSendTrapper) {
	this.groupToUniSendTrapper = groupToUniSendTrapper;
}


@Override
public String getSender() {
	return lastSender;
}

@Override
public void setSender(String newVal) {
//	duplexInputPort.setLastSender(newVal);
	lastSender = newVal;
	
}

public String getLogicalRemoteEndPoint() {
	return duplexInputPort.getLogicalRemoteEndPoint();
}
@Override
public String getPhysicalRemoteEndPoint() {
	return duplexInputPort.getPhysicalRemoteEndPoint();
}
@Override
public void setLogicalRemoteEndPoint(String newVal) {
	duplexInputPort.setLogicalRemoteEndPoint(newVal);
}
@Override
public void setPhysicalRemoteEndPoint(String newVal) {
	duplexInputPort.setPhysicalRemoteEndPoint(newVal);
}

}
