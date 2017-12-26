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
import replicatedserverport.datacomm.simplex.ClientMultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.ReplicatedServerObjectTrapperSelector;
import trace.port.AConnectionEvent;
import trace.port.AReplaceConnectionEvent;
import trace.port.ConnectiontEventBus;
import util.trace.Tracer;
// even though this port is duplex, it involves group send and receive because it is replicated
public class AReplicatedServerDuplexClientPort<MessageType> 
	extends AReplicatedServerClientPort<MessageType> 
	implements  
//	ReplicatedServerDuplexClientPort<MessageType> 
	DuplexClientInputPort<MessageType>, ReceiveListener<MessageType>
	 {
	protected ReceiveTrapper<MessageType, MessageType> uniToAllReceiveTrapper;
	
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
	public AReplicatedServerDuplexClientPort(GroupMultiServerClientPort<MessageType> aGroupSessionPort, String aLogicalServerName) {
			super(aGroupSessionPort, aLogicalServerName);

			aGroupSessionPort.addReceiveListener(this);
			
			setReceiveTrapper(createReceiveTrapper());
//			receiveTrapper = trapperSelector.createReceiveTrapper(this, receiveRegistrarAndNotifier);
			
			
	}
	
	protected ReceiveTrapper<MessageType, MessageType> createReceiveTrapper() {
		TrapperSelector trapperSelector = ReplicatedServerObjectTrapperSelector.getTrapperSelector();

//		ReceiveTrapper <MessageType, MessageType> replicatedReceiveTrapper = 
//				trapperSelector.createReceiveTrapper(this, receiveRegistrarAndNotifier);
		MultiToReplicatedTrapperSelector uniToAllTrapperSelector = 
				ClientMultiToReplicatedTrapperSelector.getTrapperSelector();
//		uniToAllReceiveTrapper =  uniToAllTrapperSelector.createReceiveTrapper(this, replicatedReceiveTrapper);
		uniToAllReceiveTrapper =  uniToAllTrapperSelector.createReceiveTrapper(this, receiveRegistrarAndNotifier);

		return uniToAllReceiveTrapper;



	}


	@Override
	public void addReceiveListener(
			ReceiveListener<MessageType> portReceiveListener) {
		receiveRegistrarAndNotifier.addReceiveListener(portReceiveListener);
	}


	@Override
	public void notifyPortReceive(String remoteEnd, MessageType message) {
		receiveTrapper.notifyPortReceive(remoteEnd, message);
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
		reply(getSender(), message);
	}
	@Override
	public void reply(String aRemoteEnd, MessageType aMessage) {
		// not sending to all as send does
//		String lastSender = getLastSender(); // this should be the physical name
		// remote end will be a logical name
//		groupMultiServerClientPort.reply(aRemoteEnd, aMessage); // even a send will work, but this is more clear
//		groupMultiServerClientPort.reply(lastSender, aMessage); // even a send will work, but this is more clear
        sendTrapper.reply(aRemoteEnd, aMessage);
	}

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
	public ReceiveReturnMessage<MessageType> receive() {
		// TODO Auto-generated method stub
		return groupMultiServerClientPort.receive();
	}

	@Override
	public ReceiveReturnMessage<MessageType> receive(String aSource) {
		// TODO Auto-generated method stub
		return groupMultiServerClientPort.receive(aSource);
	}
	
	
//	protected abstract UniToAllSendTrapper<MessageType, MessageType> createUniToAllSendTrapper(
//			InputPort anInputPort, GroupAllSender<MessageType> aDestination);
//	
//	protected abstract TrapperSelector<MessageType, MessageType> createTrapperSelector();
	

}
