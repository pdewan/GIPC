package examples.gipc.counter.customport;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectClientInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;

public class AWrappingDuplexObjectClientInputPort extends ADuplexObjectClientInputPort {

	public AWrappingDuplexObjectClientInputPort(
			DuplexClientInputPort<ByteBuffer> aBBClientInputPort) {
		super(aBBClientInputPort);
	}
	@Override
	public void send(String remoteName, Object message) {
		Object aWrappedMessage = new ATimeStampedMessage(message, System.currentTimeMillis());
		super.send(remoteName, message);	
	}

}
