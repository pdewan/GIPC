package extraip;


import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;

import multiserverport.datacomm.group.GroupMultiServerClientPort;
import port.causal.AVectorTimeStamp;
import port.causal.VectorTimeStamp;
import replicatedserverport.datacomm.duplex.AReplicatedServerDuplexClientPort;

public class AFaultTolerantReplicatedReceiveClientDuplexInputPort<MessageType> 
	extends AReplicatedServerDuplexClientPort<MessageType>
	implements DuplexClientInputPort<MessageType>, ReceiveListener<MessageType>{
//	GroupSessionPort<MessageType> groupSessionPort;
//	String logicalServerName;
	ReceiveRegistrarAndNotifier<MessageType> receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier<MessageType>();
	int messagesReceived = 0;
	VectorTimeStamp serversVectorTimeStamp = new AVectorTimeStamp();
//	String clientName;
	public AFaultTolerantReplicatedReceiveClientDuplexInputPort(GroupMultiServerClientPort<MessageType> aGroupSessionPort, String aLogicalServerName) {
			super(aGroupSessionPort, aLogicalServerName);
//			groupSessionPort = aGroupSessionPort;
//			logicalServerName = aLogicalServerName;
			groupMultiServerClientPort.addReceiveListener(this);
	}
	@Override
	public void addConnectionListener(ConnectionListener portConnectListener) {
		groupMultiServerClientPort.addConnectionListener(portConnectListener);
	}
	@Override
	public void addReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		groupMultiServerClientPort.addReceiveListener(portReceiveListener);
	}
	@Override
	public void addSendListener(ByteBufferSendListener portSendListener) {
		groupMultiServerClientPort.addSendListener(portSendListener);
	}
	@Override
	public void connect() {
		groupMultiServerClientPort.connect();
	}
	@Override
	public void disconnect() {
		groupMultiServerClientPort.disconnect();
	}
//	@Override
//	public String getLastSender() {
//		return groupSessionPort.getLastSender();
//	}
	@Override
	public String getLocalName() {
		return groupMultiServerClientPort.getLocalName();
	}
//	@Override
//	public Set<String> getRemoteEndPoints() {
//		return groupSessionPort.getRemoteEndPoints();
//	}
	@Override
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		groupMultiServerClientPort.notifyConnect(remoteEnd, aConnectionType);
	}
	@Override
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		groupMultiServerClientPort.notifyConnectFailure(remoteEnd, message, null);
	}
	@Override
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		groupMultiServerClientPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}
	@Override
	public void notifyPortReceive(String remoteEnd, MessageType message) {
		receiveRegistrarAndNotifier.notifyPortReceive(remoteEnd, message);
	}
	@Override
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		groupMultiServerClientPort.notifyPortSend(aRemoteEnd, message, sendId);
	}
	@Override
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		groupMultiServerClientPort.removeConnectionListener(portConnectListener);
	}
	@Override
	public void removeReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		receiveRegistrarAndNotifier.removeReceiveListener(portReceiveListener);
	}
	@Override
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		groupMultiServerClientPort.removeSendListener(portSendListener);
	}
	@Override
	public void reply(MessageType message) {
		groupMultiServerClientPort.reply(message);
	}
	@Override
	public void send(MessageType message) {
		groupMultiServerClientPort.sendAll(message);
	}
//	@Override
//	public void send(Set<String> clientNames, MessageType message) {
//		groupSessionPort.send(clientNames, message);
//	}
	@Override
	public void send(String remoteName, MessageType message) {
		groupMultiServerClientPort.sendAll(message);
	}
//	@Override
//	public void sendAll(MessageType message) {
//		groupSessionPort.sendAll(message);
//	}
//	public void sendOthers(MessageType message) {
//		groupSessionPort.sendOthers(message);
//	}
//	public void setLastSender(String newVal) {
//		groupSessionPort.setLastSender(newVal);
//	}
//	
	@Override
	public String getLogicalRemoteEndPoint() {
		return groupMultiServerClientPort.getLogicalRemoteEndPoint();
	}
	@Override
	public void messageReceived(String aDestination, MessageType aMessage) {
		serversVectorTimeStamp.addMessage(aDestination);
		int messagesReceivedFromCurrentServer = serversVectorTimeStamp.get(aDestination);
		if (messagesReceivedFromCurrentServer > messagesReceived) {
			messagesReceived = messagesReceivedFromCurrentServer;
			receiveRegistrarAndNotifier.notifyPortReceive(groupMultiServerClientPort.getLogicalRemoteEndPoint(), aMessage);
		}
	}
	
	
	

}
