package examples.gipc.counter.customization;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectClientInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;

public class ACustomDuplexObjectClientInputPort extends ADuplexObjectClientInputPort {

	public ACustomDuplexObjectClientInputPort(
			DuplexClientInputPort<ByteBuffer> aBBClientInputPort) {
		super(aBBClientInputPort);
	}
	@Override
	public void send(String aDestination, Object aMessage) {
		System.out.println (aDestination + "<-" + aMessage);
		super.send(aDestination, aMessage);	
	}
//	@Override
//	public ReceiveReturnMessage<Object> receive(String aSource) {
//		System.err.println("Receive started");
//		ReceiveReturnMessage retVal = super.receive(aSource);
//		System.out.println (aSource + "<-" + retVal);
//		return retVal;
//	}
}
