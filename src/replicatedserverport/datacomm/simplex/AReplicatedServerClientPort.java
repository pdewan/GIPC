package replicatedserverport.datacomm.simplex;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperSelector;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
import java.util.Set;

import multiserverport.datacomm.group.GroupMultiServerClientPort;
import port.trace.AConnectionEvent;
import port.trace.AReplaceConnectionEvent;
import port.trace.ConnectiontEventBus;
import util.trace.Tracer;

public class AReplicatedServerClientPort<MessageType> implements SimplexClientInputPort<MessageType>{
	protected SendTrapper<MessageType, MessageType> sendTrapper;
//	protected ReceiveTrapper<MessageType, MessageType> receiveTrapper;
	protected MultiToReplicatedSendTrapper<MessageType, MessageType> uniToAllSendTrapper;
	protected GroupMultiServerClientPort<MessageType> groupMultiServerClientPort;	
//	protected String logicalServerName;
//	protected TrapperSelector  trapperSelector; 
//	protected ReceiveRegistrarAndNotifier<MessageType> receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier<MessageType>();
//	int messagesReceived = 0;
//	VectorTimeStamp serversVectorTimeStamp = new AVectorTimeStamp();
//	String clientName;
	public AReplicatedServerClientPort(GroupMultiServerClientPort<MessageType> aMultiUserPort, String aLogicalServerName) {
			groupMultiServerClientPort = aMultiUserPort;
//			logicalServerName = aLogicalServerName;	
			// will allow session ports to be also used instead of multiserver port
			aMultiUserPort.setLogicalRemoteEndPoint(aLogicalServerName);
			setSendTrapper(createSendTrapper());
//			UniToGroupTrapperSelector uniToAllTrapperSelector = 
//					ClientUniToGroupSendObjectTrapperSelector.getTrapperSelector();
//			
//			uniToAllSendTrapper =  uniToAllTrapperSelector.createUniToAllSendTrapper(this, groupMultiServerClientPort);
//			Tracer.info(this, "Created uni to all trapper " + uniToAllSendTrapper + " with destination " + groupMultiServerClientPort );
//			trapperSelector = ReplicatedServerObjectTrapperSelector.getTrapperSelector();
//			sendTrapper = trapperSelector.createSendTrapper(this, uniToAllSendTrapper);
			Tracer.info(this, "Created send trapper " + sendTrapper + " with destination " + uniToAllSendTrapper );

			// we allow connect to be passed through directly to the port - an issue
			
	}
	// this cannot return anoridinar trapper but instead one that has additional operations
	// could have created two different trappers but the first one better understand
	// replies
	protected MultiToReplicatedSendTrapper<MessageType, MessageType> createSendTrapper() {
		MultiToReplicatedTrapperSelector uniToAllTrapperSelector = 
			ClientMultiToReplicatedTrapperSelector.getTrapperSelector();
	
	uniToAllSendTrapper =  uniToAllTrapperSelector.createUniToAllSendTrapper(this, groupMultiServerClientPort);
	Tracer.info(this, "Created uni to all trapper " + uniToAllSendTrapper + " with destination " + groupMultiServerClientPort );

	return uniToAllSendTrapper;
//	TrapperSelector trapperSelector = ReplicatedServerObjectTrapperSelector.getTrapperSelector();
//	return trapperSelector.createSendTrapper(this, uniToAllSendTrapper);
		
	}
	@Override
	public void addConnectionListener(ConnectionListener portConnectListener) {
		groupMultiServerClientPort.addConnectionListener(portConnectListener);
	}
//	@Override
//	public void addReceiveListener(
//			ReceiveListener<MessageType> portReceiveListener) {
//		groupMultiServerClientPort.addReceiveListener(portReceiveListener);
//	}
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
//	@Override
//	public void notifyPortReceive(String remoteEnd, MessageType message) {
//		receiveRegistrarAndNotifier.notifyPortReceive(remoteEnd, message);
//	}
	@Override
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		groupMultiServerClientPort.notifyPortSend(aRemoteEnd, message, sendId);
	}
	@Override
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		groupMultiServerClientPort.removeConnectionListener(portConnectListener);
	}
//	@Override
//	public void removeReceiveListener(
//			ReceiveListener<MessageType> portReceiveListener) {
//		receiveRegistrarAndNotifier.removeReceiveListener(portReceiveListener);
//	}
	@Override
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		groupMultiServerClientPort.removeSendListener(portSendListener);
	}
//	@Override
//	public void reply(MessageType message) {
//		groupMultiServerClientPort.reply(message);
//	}
	@Override
	public void send(MessageType message) {
//		groupMultiServerClientPort.sendAll(message);
		send(getLogicalRemoteEndPoint(), message);
	}

	public void send(String aRemoteEnd, MessageType aMessage) {
		sendTrapper.send(aRemoteEnd, aMessage);
//		groupMultiServerClientPort.sendAll(message); // this works also for serializable call, one is made but multiple are sent

	}

	@Override
	public String getLogicalRemoteEndPoint() {
		return groupMultiServerClientPort.getLogicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		return groupMultiServerClientPort.getPhysicalRemoteEndPoint();
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		groupMultiServerClientPort.setPhysicalRemoteEndPoint(newVal);
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		groupMultiServerClientPort.setLogicalRemoteEndPoint(newVal);

		
	}
	@Override
	public boolean isConnected(String remoteName) {
		return groupMultiServerClientPort.isConnected(remoteName);
	}

//	@Override
//	public void messageReceived(String aDestination, MessageType aMessage) {
//		serversVectorTimeStamp.addMessage(aDestination);
//		int messagesReceivedFromCurrentServer = serversVectorTimeStamp.get(aDestination);
//		if (messagesReceivedFromCurrentServer > messagesReceived) {
//			messagesReceived = messagesReceivedFromCurrentServer;
//			receiveRegistrarAndNotifier.notifyPortReceive(logicalServerName, aMessage);
//		}
//	}
	@Override
	public SendTrapper<MessageType, MessageType> getSendTrapper() {
		return sendTrapper;
	}
	@Override
	public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(sendTrapper.getDestination());
			Tracer.warning("send trapper == mull!");
		} else if (newVal.getDestination() == sendTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, sendTrapper, newVal, true, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
		}
		sendTrapper = newVal;
//		DistEventsBus.newEvent(new AConnectionEvent(this, sendTrapper, true));
		
	}
//	@Override
//	public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
//		return receiveTrapper;
//	}
//	@Override
//	public void setReceiveTrapper(
//			ReceiveTrapper<MessageType, MessageType> newVal) {
//		receiveTrapper = newVal;
//	}
	@Override
	public Set<String> getConnections() {
		return groupMultiServerClientPort.getConnections();
	}
//	@Override
//	public String getLastSender() {
//		return groupMultiServerClientPort.getLastSender();
//	}
//	@Override
//	public void setLastSender(String newVal) {
//		groupMultiServerClientPort.setLastSender(newVal);
//
//	}
	
//	protected abstract UniToAllSendTrapper<MessageType, MessageType> createUniToAllSendTrapper(
//			InputPort anInputPort, GroupAllSender<MessageType> aDestination);
//	
//	protected abstract TrapperSelector<MessageType, MessageType> createTrapperSelector();
	

}
