package inputport.datacomm;
import java.nio.ByteBuffer;

import port.trace.ByteBufferSent;
import port.trace.ObjectSent;

public class ASendMessageForwarder<InAndOutMessageType> extends AnAbstractSendTrapper<InAndOutMessageType, InAndOutMessageType>{
	public ASendMessageForwarder(NamingSender<InAndOutMessageType>  aDestination) {
		 super (aDestination);
	}
	@Override
	public void send(String aRemoteEndPoint, InAndOutMessageType aMessage) {
//		Tracer.info(this, "Forwarding sent message " + message);
//		destination.send(aRemoteEndPoint,  aMessage);
		if (aMessage instanceof ByteBuffer)
			ByteBufferSent.newCase(this, destination, aRemoteEndPoint, (ByteBuffer) aMessage);
		else
			ObjectSent.newCase(this, destination, aRemoteEndPoint,  aMessage);
		destination.send(aRemoteEndPoint,  aMessage);


	}
	@Override
	public Object returnValue(String aDestination, Object aMessage) {
		if (destination instanceof SendTrapper)
			return ((SendTrapper) destination).returnValue(aDestination, aMessage);
		return super.returnValue(aDestination, aMessage);
	}
	public Object getSharedSenderReceiverState() {
		if (destination instanceof SendTrapper)
			return ((SendTrapper) destination).getSharedSenderReceiverState();
		return super.getSharedSenderReceiverState();
	}
	public void setSharedSenderReceiverState(Object newVal) {
		if (destination instanceof SendTrapper)
			((SendTrapper) destination).setSharedSenderReceiverState(newVal);
		super.setSharedSenderReceiverState(newVal);
//		sharedSenderReceiverState = newVal;
	}
	
	
	
}
