package sessionport.rpc.duplex.direct.counter_example;

import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.simplex.object.ADeserializingForwarder;

public class ACustomDeserializingForwarder extends ADeserializingForwarder {
	public ACustomDeserializingForwarder(ReceiveNotifier aReceiveNotifier) {
		super(aReceiveNotifier);
	}
	public void notifySerializable(String remoteEnd, Object serializable) {
		System.out.println("Forwarding to deserializing forwarder:" + serializable);
		super.notifySerializable(remoteEnd, serializable);

	}
}
