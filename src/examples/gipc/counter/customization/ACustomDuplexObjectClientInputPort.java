package examples.gipc.counter.customization;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectClientInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
/**
 * Overrides interesting methods of the default object client input port
 */
public class ACustomDuplexObjectClientInputPort extends ADuplexObjectClientInputPort {

	public ACustomDuplexObjectClientInputPort(
			DuplexClientInputPort<ByteBuffer> aBBClientInputPort) {
		super(aBBClientInputPort);
	}
	/**
	 * Simply traces the sends dispatched to the sueprclass, does nothing more
	 */
	@Override
	public void send(String aDestination, Object aMessage) {
		System.out.println (aDestination + "<-" + aMessage);
		super.send(aDestination, aMessage);	
	}
	/**
	 * Overrides the unimplemented receive method
	 */
	@Override
	public ReceiveReturnMessage<Object> receive(String aSource) {
		System.err.println("Receive started");
		ReceiveReturnMessage retVal = super.receive(aSource);
		System.out.println (aSource + "<-" + retVal);
		return retVal;
	}
}
