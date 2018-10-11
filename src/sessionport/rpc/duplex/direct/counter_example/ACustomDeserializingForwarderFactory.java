package sessionport.rpc.duplex.direct.counter_example;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.simplex.object.ADeserializingForwarderFactory;
import inputport.datacomm.simplex.object.DeserializingForwarder;

public class ACustomDeserializingForwarderFactory extends ADeserializingForwarderFactory {	
	@Override
	public DeserializingForwarder createReceiveTrapper(InputPort anInputPort,
			ReceiveNotifier<Object> receiveRegistrarAndNotifier) {
		return new ACustomDeserializingForwarder (receiveRegistrarAndNotifier);
	}
}
