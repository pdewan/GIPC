package inputport.datacomm.simplex.object;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;

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
