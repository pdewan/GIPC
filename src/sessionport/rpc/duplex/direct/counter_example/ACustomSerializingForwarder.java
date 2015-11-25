package sessionport.rpc.duplex.direct.counter_example;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import util.trace.Tracer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.simplex.object.ASerializingForwarder;

public class ACustomSerializingForwarder extends ASerializingForwarder {
	

	public ACustomSerializingForwarder(InputPort anInputPort,
			NamingSender<ByteBuffer> aDestination) {
		super(anInputPort, aDestination);
	}
	
	public void send(String remoteName, Object aMessage) {
//		Object anActualSentMessage = aMessage;
		System.out.println("Forwarding to serializing forwarder" + aMessage);
		super.send(remoteName, aMessage);

		
	}

}
