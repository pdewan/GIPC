package sessionport.rpc.duplex.direct.counter_example;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.simplex.object.ADeserializingForwarder;
import inputport.datacomm.simplex.object.ADeserializingForwarderFactory;
import inputport.datacomm.simplex.object.ASerializingForwarder;
import inputport.datacomm.simplex.object.ASerializingForwarderFactory;
import inputport.datacomm.simplex.object.DeserializingForwarder;

public class ACustomDeserializingForwarderFactory extends ADeserializingForwarderFactory {
	
	@Override
	public DeserializingForwarder createReceiveTrapper(InputPort anInputPort,
			ReceiveNotifier<Object> receiveRegistrarAndNotifier) {
		return new ACustomDeserializingForwarder (receiveRegistrarAndNotifier);
	}
}
