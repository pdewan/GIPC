package inputport.datacomm.simplex.object;
import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapperFactory;

public interface SerializingForwarderFactory extends SendTrapperFactory<Object, ByteBuffer>{
	SerializingForwarder createSendTrapper(InputPort anInputPort, NamingSender<ByteBuffer>  aDestination);
}
