package replicatedserverport.datacomm.duplex;

import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.TrapperSelector;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.util.List;

import multiserverport.datacomm.group.GroupMultiServerClientPort;
import replicatedserverport.datacomm.simplex.AReplicatedServerClientPort;
import replicatedserverport.datacomm.simplex.ReplicatedServerObjectTrapperSelector;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;
// even though this port is duplex, it involves group send and receive because it is replicated
public class CopyOfAReplicatedServerDuplexClientPort<MessageType> 
	extends AReplicatedServerClientPort<MessageType> 
	implements  
//	ReplicatedServerDuplexClientPort<MessageType> 
	DuplexClientInputPort<MessageType>, ReceiveListener<MessageType>
	 {
	
	String currentServer;
//	protected SendTrapper<MessageType, MessageType> sendTrapper;
	protected ReceiveTrapper<MessageType, MessageType> receiveTrapper;
//	protected UniToAllSendTrapper<MessageType, MessageType> uniToAllSendTrapper;
//	protected GroupMultiServerClientPort<MessageType> groupMultiServerClientPort;	
//	protected String logicalServerName;
	protected ReceiveRegistrarAndNotifier<MessageType> receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier<MessageType>();
//	int messagesReceived = 0;
//	VectorTimeStamp serversVectorTimeStamp = new AVectorTimeStamp();
//	String clientName;
	public CopyOfAReplicatedServerDuplexClientPort(GroupMultiServerClientPort<MessageType> aGroupSessionPort, String aLogicalServerName) {
			super(aGroupSessionPort, aLogicalServerName);

			aGroupSessionPort.addReceiveListener(this);
			
			setReceiveTrapper(createReceiveTrapper());
//			receiveTrapper = trapperSelector.createReceiveTrapper(this, receiveRegistrarAndNotifier);
			
			
	}
	protected ReceiveTrapper<MessageType, MessageType> createReceiveTrapper() {
		TrapperSelector trapperSelector = ReplicatedServerObjectTrapperSelector.getTrapperSelector();

		return 	trapperSelector.createReceiveTrapper(this, receiveRegistrarAndNotifier);

	}

//	@Override
//	public void addConnectListener(ConnectListener portConnectListener) {
//		groupMultiServerClientPort.addConnectListener(portConnectListener);
//	}
	@Override
	public void addReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		receiveRegistrarAndNotifier.addReceiveListener(portReceiveListener);
	}
//	@Override
//	public void addSendListener(ByteBufferSendListener portSendListener) {
//		groupMultiServerClientPort.addSendListener(portSendListener);
//	}
//	@Override
//	public void connect() {
//		groupMultiServerClientPort.connect();
//	}
//	@Override
//	public void disconnect() {
//		groupMultiServerClientPort.disconnect();
//	}
//	@Override
//	public String getLastSender() {
//		return groupSessionPort.getLastSender();
//	}
//	@Override
//	public String getLocalName() {
//		return groupMultiServerClientPort.getLocalName();
//	}
//	@Override
//	public Set<String> getRemoteEndPoints() {
//		return groupSessionPort.getRemoteEndPoints();
//	}
//	@Override
//	public void notifyConnect(String remoteEnd) {
//		groupMultiServerClientPort.notifyConnect(remoteEnd);
//	}
//	@Override
//	public void notifyConnectFailure(String remoteEnd, String message) {
//		groupMultiServerClientPort.notifyConnectFailure(remoteEnd, message);
//	}
//	@Override
//	public void notifyDisconnect(String remoteEnd, boolean eof,
//			String closeReason) {
//		groupMultiServerClientPort.notifyDisconnect(remoteEnd, eof, closeReason);
//	}
	@Override
	public void notifyPortReceive(String remoteEnd, MessageType message) {
		receiveTrapper.notifyPortReceive(remoteEnd, message);
	}
//	@Override
//	public void notifyPortSend(ByteBuffer message, int sendId) {
//		groupMultiServerClientPort.notifyPortSend(message, sendId);
//	}
//	@Override
//	public void removeConnectListener(ConnectListener portConnectListener) {
//		groupMultiServerClientPort.removeConnectListener(portConnectListener);
//	}
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
//	@Override
//	public void send(MessageType message) {
//		groupMultiServerClientPort.sendAll(message);
//	}
//	@Override
//	public void send(Set<String> clientNames, MessageType message) {
//		groupSessionPort.send(clientNames, message);
//	}
//	public void send(String remoteName, MessageType message) {
//		groupMultiServerClientPort.sendAll(message);
//	}
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
//	@Override
//	public String getRemoteEndPoint() {
//		return logicalServerName;
//	}
//	@Override
//	public void messageReceived(String aDestination, MessageType aMessage) {
//		serversVectorTimeStamp.addMessage(aDestination);
//		int messagesReceivedFromCurrentServer = serversVectorTimeStamp.get(aDestination);
//		if (messagesReceivedFromCurrentServer > messagesReceived) {
//			messagesReceived = messagesReceivedFromCurrentServer;
//			receiveRegistrarAndNotifier.notifyPortReceive(logicalServerName, aMessage);
//		}
//	}
//	@Override
//	public SendTrapper<MessageType, MessageType> getSendTrapper() {
//		return sendTrapper;
//	}
//	@Override
//	public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
//		sendTrapper = newVal;
//		
//	}
	@Override
	public ReceiveTrapper<MessageType, MessageType> getReceiveTrapper() {
		return receiveTrapper;
	}
	@Override
	public void setReceiveTrapper(
			ReceiveTrapper<MessageType, MessageType> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(newVal.getDestination());
			Tracer.warning("receive trapper == mull!");
		} else if (newVal.getDestination() == receiveTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, receiveTrapper, newVal, false, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, false));
		}
		receiveTrapper = newVal;
//		DistEventsBus.newEvent(new AConnectionEvent(this, receiveTrapper, false));
	}
//	@Override
//	public Set<String> getConnections() {
//		return groupMultiServerClientPort.getConnections();
//	}
	@Override
	public String getSender() {
		return groupMultiServerClientPort.getSender();
	}
	@Override
	public void setSender(String newVal) {
		groupMultiServerClientPort.setSender(newVal);

	}
	
	
	static {
//		GlobalState.getReplicatedServerDuplexObjectTrapperSelector().setReceiveTrapperFactory(new AnEarliestAcceptingMultiToUniReceiveForwarderFactory());
//		ReplicatedServerObjectTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(new AnEarliestAcceptingMultiToUniReceiveForwarderFactory());
//		ReplicatedServerObjectTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(new AnAllAcceptingMultiToUniReceiveForwarderFactory());

	}

	@Override
	public void messageReceived(String remoteClientName, MessageType message) {
		notifyPortReceive(remoteClientName, message);
		
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		currentServer = newVal;
		
	}
	
	public String getPhysicalRemoteEndPoint() {
		return currentServer;
	}
	@Override
	public List<ReceiveListener<MessageType>> getReceiveListeners() {
		return receiveRegistrarAndNotifier.getReceiveListeners();
	}
	@Override
	public void reply(String aRemoteEnd, MessageType aMessage) {
		send(aRemoteEnd, aMessage);
	}
	@Override
	public ReceiveReturnMessage<MessageType> receive() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReceiveReturnMessage<MessageType> receive(String aSource) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
//	protected abstract UniToAllSendTrapper<MessageType, MessageType> createUniToAllSendTrapper(
//			InputPort anInputPort, GroupAllSender<MessageType> aDestination);
//	
//	protected abstract TrapperSelector<MessageType, MessageType> createTrapperSelector();
	

}
