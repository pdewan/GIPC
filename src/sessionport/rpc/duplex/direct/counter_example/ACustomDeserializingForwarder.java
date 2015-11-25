package sessionport.rpc.duplex.direct.counter_example;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import port.delay.AMessageWithDestination;
import port.delay.MessageWithDestination;
import util.trace.Tracer;
import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.simplex.object.ADeserializingForwarder;
import inputport.datacomm.simplex.object.ASerializingForwarder;

public class ACustomDeserializingForwarder extends ADeserializingForwarder {


	public ACustomDeserializingForwarder(ReceiveNotifier aReceiveNotifier) {
		super(aReceiveNotifier);

	}

	public void notifySerializable(String remoteEnd, Object serializable) {
		System.out.println("Forwarding to deserializing forwarder:" + serializable);
		super.notifySerializable(remoteEnd, serializable);

	}

	
	


}
