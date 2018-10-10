package multiserverport.datacomm.group.object;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Collection;

import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.group.AnAbstractGroupMultiServerClientPort;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;




public  class AnObjectGroupMultiServerClientPort extends 
		AnAbstractGroupMultiServerClientPort<Object> implements GroupMultiServerClientPort<Object> {
	protected DuplexMultiServerClientPort<Object> duplexMultiServerClientPort;
	protected GroupMultiServerClientPort<ByteBuffer> bbGroupMultiServerClientPort;

	protected GroupSendTrapper<Object, ByteBuffer> groupSerializer;
	GroupNamingSender<Object> sendTrapperDestination;

	
//	UniSender<ByteBuffer> uniSender;
	public AnObjectGroupMultiServerClientPort(
			DuplexMultiServerClientPort<Object> aDuplexMultiServerClientPort, 
			GroupMultiServerClientPort<ByteBuffer> aBBgroupMultiServerClientPort) {
		super(aDuplexMultiServerClientPort);
//		groupSendTrapper = GlobalState.getServerObjectGroupTrapperSelector().createGroupSendTrapper(this, aDestination);
		duplexMultiServerClientPort = aDuplexMultiServerClientPort;
//		bufferSerializationSupport = BufferSerializationSupportPoolSelector.createBufferSerializationSupportPool(this);
		bbGroupMultiServerClientPort = aBBgroupMultiServerClientPort;		
		if (bbGroupMultiServerClientPort == null) {
			groupToUniSendTrapper = MultiServerGroupToUniSendSendObjectTrapperSelector.getTrapperSelector().
					createGroupToUniSendTrapper(this, duplexMultiServerClientPort);
//			groupToUniSendTrapper = GlobalState.getMultiServerGroupToUniSendObjectTrapperSelector().createGroupToUniSendTrapper(this, duplexMultiServerClientPort);

			sendTrapperDestination = groupToUniSendTrapper;


			
		} else {
//			groupSerializer = GlobalState.getMultiServerObjectGroupTranslatingIPTrapperSelector().createGroupSendTrapper(this, bbGroupMultiServerClientPort);
			groupSerializer = MultiServerObjectGroupTranslatingIPTrapperSelector.getTrapperSelector().createGroupSendTrapper(this, bbGroupMultiServerClientPort);

			
//			groupSerializer = createTranslatingSendTrappper();

			sendTrapperDestination = groupSerializer; 
			ConnectiontEventBus.receiveOnlySource(duplexMultiServerClientPort);


		}
//		groupSendTrapper = GlobalState.getMultiServerObjectGroupTrapperSelector().createGroupSendTrapper(this, sendTrapperDestination);
//		groupSendTrapper = MultiServerObjectGroupTrapperSelector.getTrapperSelector().
//				createGroupSendTrapper(this, sendTrapperDestination);
		setGroupSendTrapper(createGroupSendTrapper());

//		receiveTrapper = GlobalState.getMultiServerObjectGroupTrapperSelector().createReceiveTrapper(this, receiveRegistrarAndNotifier);
//		receiveTrapper = MultiServerObjectGroupTrapperSelector.getTrapperSelector().
//				createReceiveTrapper(this, receiveRegistrarAndNotifier);
		setReceiveTrapper(createReceiveTrapper());

	}	
	protected GroupSendTrapper<Object,  Object> createGroupSendTrapper () {
		return MultiServerObjectGroupTrapperSelector.getTrapperSelector().
		createGroupSendTrapper(this, sendTrapperDestination);
	}
	protected ReceiveTrapper<Object, Object> createReceiveTrapper() {
		return MultiServerObjectGroupTrapperSelector.getTrapperSelector().
		createReceiveTrapper(this, receiveRegistrarAndNotifier);
	}
	
	@Override
	public void send(Collection<String> clientNames, Object message) {
		if (clientNames.size() == 0) return;
//		ByteBuffer message = bufferSerializationSupport.outputBufferFromSerializable(object);
//		bbGroupServerInputPort.send(clientNames, message);
//		if (message instanceof  ByteBuffer) {
////			bbGroupServerInputPort.send(clientNames, (ByteBuffer) message); 
//			Message.info(this, "Cannot send byte buffer on object port");
//			return;
//		}
		if (!(message instanceof Serializable)) {
			Tracer.info(this, "Can only send seralizable on object group port");
			return;
		}
		Tracer.info(this, "Sending group message:" + message + " to:" + clientNames);
//		unOptimizedObjectForwarder.send(remoteName, message);
//		if (bbGroupServerInputPort != null)
//			sendOptimized(clientNames, message);
//		else 
//			sendUnoptimized(clientNames, message);
		groupSendTrapper.send(clientNames, message);
//		if (bbGroupServerInputPort != null)
//			optimizedObjectForwarder.send(clientNames, message);
//		else {
//			for (String clientName:clientNames) {
//				unOptimizedObjectForwarder.send(clientName, message);
//			}
//		}
	}
		

//	void sendUnoptimized(Set<String> clientNames, Object message) {
//		for (String clientName:clientNames) {
//			duplexServerInputPort.send(clientName, message);
//		}
//	}
//
//	
//	void sendOptimized(Set<String> clientNames, Object message) {		
//		ByteBuffer bbMessage = bufferSerializationSupport.outputBufferFromObject((Serializable) message);		
//		bbGroupServerInputPort.send(clientNames, bbMessage);
//	}
//	
	
//	
	@Override
	public void setGroupSendTrapper(
			GroupSendTrapper<Object, Object> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(groupSendTrapper.getDestination());
			Tracer.warning("group send  trapper == mull!");
		} else if (newVal.getDestination() == groupSendTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, groupSendTrapper, newVal, true, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
		}

		this.groupSendTrapper = newVal;
		
//		DistEventsBus.newEvent(new AConnectionEvent(this, newVal, true));
//		groupSendTrapper.setDestination(sendTrapperDestination);
	}
	
	@Override
	public String getSender() {
		return bbGroupMultiServerClientPort.getSender();
	}

	@Override
	public void setSender(String newVal) {
//		duplexInputPort.setLastSender(newVal);
		bbGroupMultiServerClientPort.setSender(newVal);
		
	}
	@Override
	public ReceiveReturnMessage<Object> receive() {
		return receive(getSender());
	}

	@Override
	public ReceiveReturnMessage<Object> receive(String aSource) {
		System.err.println("Receive not implemented");
		return null;
	}
//	
//	@Override
//	public ReceiveNotifier<Object> getReceiveTrapper() {
//		return null;
//	}
//	@Override
//	public void setReceiveTrapper(ReceiveNotifier<Object> newVal) {
////		bbGroupServerInputPort.setReceiveTrapper(newVal);
//	}
//	@Override
//	public ip.UniNamingSender<Object> getSendTrapper() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public void setSendTrapper(ip.UniNamingSender<Object> newVal) {
//		// TODO Auto-generated method stub
//		
//	}
//	static {
//		GlobalState.getMultiServerObjectGroupTranslatingIPTrapperSelector().setGroupSendTrapperFactory(new ASerializingGroupForwarderFactory());
//	
//	
//	}
	

}
