package inputport.datacomm.simplex.object;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.SendTrapperFactory;

import java.nio.ByteBuffer;

public class ASerializingForwarderFactory implements SendTrapperFactory<Object, ByteBuffer> {

	@Override
	public SendTrapper<Object, ByteBuffer> createSendTrapper(InputPort anInputPort,
			NamingSender<ByteBuffer> aDestination) {
		return new ASerializingForwarder(anInputPort, aDestination);
	}

}
