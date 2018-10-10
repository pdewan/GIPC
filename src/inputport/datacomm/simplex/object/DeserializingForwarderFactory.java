package inputport.datacomm.simplex.object;
import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapperFactory;

public interface DeserializingForwarderFactory extends ReceiveTrapperFactory<ByteBuffer, Object>{
	DeserializingForwarder createReceiveTrapper(InputPort anInputPort, ReceiveNotifier<Object> aReceiveNotifier) ;
}
