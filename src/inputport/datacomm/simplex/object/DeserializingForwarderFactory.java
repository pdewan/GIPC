package inputport.datacomm.simplex.object;
import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;

import java.nio.ByteBuffer;

public interface DeserializingForwarderFactory extends ReceiveTrapperFactory<ByteBuffer, Object>{
	DeserializingForwarder createReceiveTrapper(InputPort anInputPort, ReceiveNotifier<Object> aReceiveNotifier) ;
}
