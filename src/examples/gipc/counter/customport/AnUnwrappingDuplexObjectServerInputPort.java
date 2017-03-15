package examples.gipc.counter.customport;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectServerInputPort;

public class AnUnwrappingDuplexObjectServerInputPort extends ADuplexObjectServerInputPort{

	public AnUnwrappingDuplexObjectServerInputPort(
			DuplexServerInputPort<ByteBuffer> aBBDuplexServerInputPort) {
		super(aBBDuplexServerInputPort);
	}
	@Override
	public void messageReceived(String remoteClientName, ByteBuffer message) {
	}

}
