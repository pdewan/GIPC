package sessionport.rpc.duplex.direct.counter_example;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.simplex.object.ASerializingForwarder;
import inputport.datacomm.simplex.object.ASerializingForwarderFactory;
import inputport.datacomm.simplex.object.SerializingForwarder;

public class ACustomSerializingForwarderFactory extends ASerializingForwarderFactory {
	SerializingForwarder lastSendTrapper;
	@Override
	public SerializingForwarder createSendTrapper(InputPort anInputPort,
			NamingSender<ByteBuffer> aDestination) {
//		lastSendTrapper = new  AFaultTolerantSerializingForwarder(anInputPort, aDestination);
		return new ACustomSerializingForwarder(anInputPort, aDestination);
//		return lastSendTrapper;

	}
//	@Override
//	public SendTrapper<Object, ByteBuffer> getLastSendTrapper() {
//		return lastSendTrapper;
//	}


}
