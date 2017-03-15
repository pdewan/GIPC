package examples.gipc.counter.customization;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectClientInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;

public class ACustomDuplexObjectClientInputPort extends ADuplexObjectClientInputPort {

	public ACustomDuplexObjectClientInputPort(
			DuplexClientInputPort<ByteBuffer> aBBClientInputPort) {
		super(aBBClientInputPort);
	}
	@Override
	public void send(String remoteName, Object message) {
		Object aWrappedMessage = new ATimeStampedMessage(message, System.currentTimeMillis());
		System.out.println ("Wrapped:" + aWrappedMessage);
		super.send(remoteName, message);	
	}

}
