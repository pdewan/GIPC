package inputport.datacomm;

import java.nio.ByteBuffer;

import inputport.InputPort;
import util.trace.port.buffer.TrapperBufferReceived;
import util.trace.port.objects.TrapperObjectReceived;



public class AReceiveMessageForwarder<InAndOutMessageType> extends AnAbstractReceiveTrapper<InAndOutMessageType, InAndOutMessageType> {
	public AReceiveMessageForwarder(InputPort anInputPort, ReceiveNotifier<InAndOutMessageType> aReceiveNotifier) {
		 super (null, aReceiveNotifier);
	}
	@Override
	public void notifyPortReceive(String aRemoteEnd, InAndOutMessageType aMessage) {
	
//		Tracer.info(this, "Forwarding received message " + message +  " from: " + remoteEnd);
//		destination.notifyPortReceive(aRemoteEnd, aMessage);	
		if (aMessage instanceof ByteBuffer)
			TrapperBufferReceived.newCase(this, destination, aRemoteEnd, (ByteBuffer) aMessage);
		else
			TrapperObjectReceived.newCase(this, destination, aRemoteEnd,  aMessage);
		destination.notifyPortReceive(aRemoteEnd, aMessage);	

	}	
	
}
