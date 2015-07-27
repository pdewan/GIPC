package inputport.datacomm.simplex.object;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;

import java.nio.ByteBuffer;

public class ADeserializingForwarderFactory implements 
	//ReceiveTrapperFactory<ByteBuffer, Object> {
	DeserializingForwarderFactory {
	
//	@Override
//	public ReceiveTrapper<ByteBuffer, Object> createReceiveTrapper(InputPort anInputPort,
//			ReceiveNotifier<Object> receiveRegistrarAndNotifier) {
//		return new ADeserializingForwarder (receiveRegistrarAndNotifier);
//	}
	
	public DeserializingForwarder createReceiveTrapper(InputPort anInputPort,
			ReceiveNotifier<Object> receiveRegistrarAndNotifier) {
		return new ADeserializingForwarder (receiveRegistrarAndNotifier);
	}

	
}
