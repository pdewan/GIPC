package examples.gipc.counter.customization;

import java.nio.ByteBuffer;

import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;

public class ACustomDuplexObjectServerInputPort extends ADuplexObjectServerInputPort{

	public ACustomDuplexObjectServerInputPort(
			DuplexServerInputPort<ByteBuffer> aBBDuplexServerInputPort) {
		super(aBBDuplexServerInputPort);
	}
	/**
	 * Changes the notifier that invokes receive listeners
	 */
	@Override
	protected ReceiveRegistrarAndNotifier<Object> createReceiveRegistrarAndNotifier() {
		return new ACustomReceiveNotifier();
	}
	
	/**
	 * Overrides the unimplemented paramaterized receive method
	 */
	@Override
	public ReceiveReturnMessage<Object> receive(String aSource) {
		System.err.println("Receive started");
		ReceiveReturnMessage retVal = super.receive(aSource);
		System.out.println (aSource + "<-" + retVal);
		return retVal;
	}

}
