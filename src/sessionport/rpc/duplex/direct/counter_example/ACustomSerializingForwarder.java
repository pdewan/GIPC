package sessionport.rpc.duplex.direct.counter_example;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.object.ASerializingForwarder;

public class ACustomSerializingForwarder extends ASerializingForwarder {
	public ACustomSerializingForwarder(InputPort anInputPort,
			NamingSender<ByteBuffer> aDestination) {
		super(anInputPort, aDestination);
	}
	@Override
	public void send(String remoteName, Object aMessage) {
		System.out.println("Forwarding to serializing forwarder" + aMessage);
		super.send(remoteName, aMessage);		
	}
}
