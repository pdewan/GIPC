package examples.gipc.counter.customization;

import java.nio.ByteBuffer;

import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectServerInputPort;

public class ACustomDuplexObjectServerInputPort extends ADuplexObjectServerInputPort{

	public ACustomDuplexObjectServerInputPort(
			DuplexServerInputPort<ByteBuffer> aBBDuplexServerInputPort) {
		super(aBBDuplexServerInputPort);
	}
	/**
	 * Changes the notifier that invokes receive listeners
	 */
	protected ReceiveRegistrarAndNotifier<Object> createReceiveRegistrarAndNotifier() {
		return new ACustomReceiveNotifier();
	}

}
