package inputport.datacomm.simplex.object;
import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;
import inputport.datacomm.SendTrapperFactory;

import java.nio.ByteBuffer;

public interface SerializingForwarderFactory extends SendTrapperFactory<Object, ByteBuffer>{
	SerializingForwarder createSendTrapper(InputPort anInputPort, NamingSender<ByteBuffer>  aDestination);
}
