package inputport.datacomm.duplex.object;

import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.object.ASimplexObjectServerInputPort;
import inputport.datacomm.simplex.object.ObjectTranslatingIPTrapperSelector;
import inputport.datacomm.simplex.object.ServerObjectTrapperSelector;

import java.nio.ByteBuffer;
import java.util.Set;

import port.trace.AConnectionEvent;
import port.trace.ConnectiontEventBus;





public class ADuplexObjectServerInputPort extends ASimplexObjectServerInputPort implements DuplexServerInputPort<Object>{
	SimplexServerInputPort<ByteBuffer> bbServerInputPort;

	DuplexServerInputPort<ByteBuffer> bbDuplexServerInputPort;
	protected SendTrapper<Object, ByteBuffer> serializer;
	protected SendTrapper<Object, Object> singleMessageForwarder;
	

	public ADuplexObjectServerInputPort(DuplexServerInputPort<ByteBuffer> aBBDuplexServerInputPort) {		
		super(aBBDuplexServerInputPort);
		bbDuplexServerInputPort = aBBDuplexServerInputPort; 
		serializer = ObjectTranslatingIPTrapperSelector.getTrapperSelector().createSendTrapper(this, bbDuplexServerInputPort);
//		serializer = GlobalState.getObjectTranslatingIPTrapperSelector().createSendTrapper(this, bbDuplexServerInputPort);

//		singleMessageForwarder = ObjectForwardingIPTrapperSelector.getTrapperSelector().createSendTrapper(this, serializer);
//		setSendTrapper (ClientObjectTrapperSelector.getTrapperSelector().createSendTrapper(this, serializer));
//		setSendTrapper (ServerObjectTrapperSelector.getTrapperSelector().createSendTrapper(this, serializer));
		setSendTrapper(createSendTrapper());
	}
	protected SendTrapper<Object, Object> createSendTrapper() {
		return ServerObjectTrapperSelector.getTrapperSelector().createSendTrapper(this, serializer);
	}
//	BufferSerializationSupport createBufferSerializationSupport(){
//		return  BufferSerializationSupportPoolSelector.createBufferSerializationSupportPool(
//				(DuplexServerInputPort<ByteBuffer>) bbServerInputPort );
//
//	}
	
	@Override
	public void reply(Object message) {
		String lastSender = bbDuplexServerInputPort.getSender();
		if (lastSender == null)	return;
		send(lastSender, message);
		
	}
	@Override
	public void send(String remoteName, Object message) {
		// the client port does not check this, so why should the server port do so. Why not send byte buffers on object port?
		// let serializer simply pass the object ad let the deserializer fail if necessary and send the byte buffer
//		if (message instanceof  ByteBuffer) {
//			Tracer.error("Cannot send byte buffer on object port");
//		} else if (message instanceof Serializable || message == null) {
//
//			singleMessageForwarder.send(remoteName, message);
//		} else {
//			Tracer.error("Sent message is not serializable");
//		}
		
		singleMessageForwarder.send(remoteName, message);

	}
	@Override
	public void send(Object message) {
		reply(message);
		
	}
	@Override
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		bbDuplexServerInputPort.notifyPortSend(aRemoteEnd, message, sendId);
		
	}
	@Override
	public void addSendListener(ByteBufferSendListener portSendListener) {
		bbDuplexServerInputPort.addSendListener(portSendListener);
		
	}
	@Override
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		bbDuplexServerInputPort.removeSendListener(portSendListener);
		
	}
	@Override
	public String getSender() {
		return bbDuplexServerInputPort.getSender();
	}
	@Override
	public Set<String> getConnections() {
		return bbDuplexServerInputPort.getConnections();
	}
	@Override
	public void setSender(String newVal) {
		bbDuplexServerInputPort.setSender(newVal);		
	}
//	@Override
	public DuplexServerInputPort<ByteBuffer> getDuplexBufferServerInputPort() {
		return bbDuplexServerInputPort;
	}
//	@Override
	public SendTrapper<Object, Object> getSendTrapper() {
		// TODO Auto-generated method stub
		return singleMessageForwarder;
	}

//	@Override
	public void setSendTrapper(SendTrapper<Object, Object> newVal) {
		singleMessageForwarder = newVal;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, singleMessageForwarder, true));		
	}
	
	@Override
	public void reply(String aRemoteEnd, Object aMessage) {
		send(aRemoteEnd, aMessage);
	}
	
	
//	public static void main (String[] args) {
//		Tracer.showInfo(true);
//		DuplexServerInputPort<Object> serverInputPort = ObjectDuplexInputPortSelector.createDuplexServerInputPort ("9090", "test server");
//		serverInputPort.connect();
//		PrintingReplyingObjectReceiver printingReplyngTypedReceiver = new PrintingReplyingObjectReceiver(serverInputPort);
//		serverInputPort.addConnectionListener(printingReplyngTypedReceiver);
////		serverInputPort.addDisconnectListener(printingReplyngTypedReceiver);
//		serverInputPort.addReceiveListener(printingReplyngTypedReceiver);	
////		serverInputPort.addSendListener(printingReplyngTypedReceiver);
//	}
//	@Override
//	public UniNamingSender<Object> getSendTrapper() {
//		return objectForwarder;
//	}
//	@Override
//	public void setSendTrapper(UniNamingSender<Object> newVal) {
//		objectForwarder = newVal;		
//	}
	
	
	// why repeat this from client input port?
	// why not put in GlobalState?
	static {
//		GlobalState.getObjectTranslatingIPTrapperSelector().
//			setSendTrapperFactory(new ASerializingForwarderFactory());
//		ObjectTranslatingIPTrapperSelector.getTrapperSelector().
//		setSendTrapperFactory(new ASerializingForwarderFactory());
	}	
	
	

}
