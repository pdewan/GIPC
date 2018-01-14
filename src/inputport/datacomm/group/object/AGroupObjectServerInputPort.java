package inputport.datacomm.group.object;

import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.group.AnAbstractGroupServerInputPort;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupServerInputPort;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Set;

import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.ConnectiontEventBus;




public  class AGroupObjectServerInputPort extends 
		AnAbstractGroupServerInputPort<Object> {
	protected DuplexServerInputPort<Object> duplexServerInputPort;
//	BufferSerializationSupport bufferSerializationSupport;
	protected GroupServerInputPort<ByteBuffer> bbGroupServerInputPort;
//	protected GroupNamingSender<Object> serializingGroupForwarder;
//	protected GroupNamingSender<Object> multipleSendGroupForwarder;
//	protected UniNamingSender<Object> unOptimizedObjectForwarder;
//	protected GroupNamingSender<Object> optimizedObjectForwarder;
//	protected GroupNamingSender<Object> groupSendTrapper;
	protected GroupSendTrapper<Object, ByteBuffer> groupSerializer;
	GroupNamingSender<Object> sendTrapperDestination;

	
//	UniSender<ByteBuffer> uniSender;
	public AGroupObjectServerInputPort(DuplexServerInputPort<Object> aDuplexServerInputPort, GroupServerInputPort<ByteBuffer> aBBgroupServerInputPort) {
		super(aDuplexServerInputPort);
//		groupSendTrapper = GlobalState.getServerObjectGroupTrapperSelector().createGroupSendTrapper(this, aDestination);
		duplexServerInputPort = aDuplexServerInputPort;
//		bufferSerializationSupport = BufferSerializationSupportPoolSelector.createBufferSerializationSupportPool(this);
		bbGroupServerInputPort = aBBgroupServerInputPort;		
		if (bbGroupServerInputPort == null) {
			groupToUniSendTrapper = ServerGroupToUniSendObjectTrapperSelector.getTrapperSelector().createGroupToUniSendTrapper(this, duplexServerInputPort);

//			groupToUniSendTrapper = GlobalState.getServerGroupToUniSendObjectTrapperSelector().createGroupToUniSendTrapper(this, duplexServerInputPort);
			sendTrapperDestination = groupToUniSendTrapper;


			
		} else {
			groupSerializer = ServerObjectGroupTranslatingIPTrapperSelector.getTrapperSelector().createGroupSendTrapper(this, bbGroupServerInputPort);


			sendTrapperDestination = groupSerializer; 
			ConnectiontEventBus.receiveOnlySource(duplexServerInputPort);
			
		}
//		groupSendTrapper = ServerObjectGroupTrapperSelector.getTrapperSelector().
//				createGroupSendTrapper(this, sendTrapperDestination);
		setGroupSendTrapper(createGroupSendTrapper());
//		groupSendTrapper = GlobalState.getServerObjectGroupTrapperSelector().createGroupSendTrapper(this, sendTrapperDestination);

//		receiveTrapper = GlobalState.getServerObjectGroupTrapperSelector().createReceiveTrapper(this, receiveRegistrarAndNotifier);
//		receiveTrapper = ServerObjectGroupTrapperSelector.getTrapperSelector().
//				createReceiveTrapper(this, receiveRegistrarAndNotifier);
		setReceiveTrapper(createReceiveTrapper());

	}	
	protected GroupSendTrapper<Object, Object> createGroupSendTrapper() {
		return ServerObjectGroupTrapperSelector.getTrapperSelector().
		createGroupSendTrapper(this, sendTrapperDestination);
	}
	protected ReceiveTrapper<Object, Object> createReceiveTrapper() {
		return ServerObjectGroupTrapperSelector.getTrapperSelector().
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
		
		// commenting this out as am not sure why this constraint holds
//		if (!(message instanceof Serializable)) {
//			Tracer.info(this, "Can only send seralizable on object group port");
//			return;
//		}
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
	
//	@Override
	public GroupServerInputPort<ByteBuffer> getUntypedGroupServerInputPort() {
		// TODO Auto-generated method stub
		return bbGroupServerInputPort;
	}
	@Override
	public void setGroupSendTrapper(
			GroupSendTrapper<Object, Object> newVal) {
		this.groupSendTrapper = newVal;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
//		groupSendTrapper.setDestination(sendTrapperDestination);
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
//	public static void main (String[] args) {
//		Tracer.showInfo(true);
//		ByteBuffer bb = ByteBuffer.wrap("hello test".getBytes());
//		System.out.println(bb instanceof Serializable);
////		TypedGroupServerInputPort serverInputPort = new AnInheritingTypedGroupServerInputPort("9090", "test server");
//		GroupServerInputPort<Object> serverInputPort = GroupObjectInputPortSelector.createGroupServerInputPort ("9090", "test server");
//		serverInputPort.connect();
//		PrintingReplyingGroupSendingTypedReceiver printingReplyngTypedReceiver = new PrintingReplyingGroupSendingTypedReceiver(serverInputPort);
//		serverInputPort.addConnectionListener(printingReplyngTypedReceiver);
////		serverInputPort.addDisconnectListener(printingReplyngTypedReceiver);
//		serverInputPort.addReceiveListener(printingReplyngTypedReceiver);	
////		serverInputPort.addSendListener(printingReplyngTypedReceiver);
//	}
//	static {
//		GlobalState.getServerObjectGroupTranslatingIPTrapperSelector().setGroupSendTrapperFactory(new ASerializingGroupForwarderFactory());
//	
//	
//	}
	
	public ReceiveReturnMessage<Object> receive() {
		return receive(getSender());		

	}
	@Override
	public ReceiveReturnMessage<Object> receive(String aSource) {
		System.err.println("Receive not implemented");
		return null;
	}
}
