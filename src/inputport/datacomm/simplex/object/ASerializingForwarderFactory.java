package inputport.datacomm.simplex.object;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.SendTrapperFactory;

import java.nio.ByteBuffer;

public class ASerializingForwarderFactory implements 
//	SendTrapperFactory<Object, ByteBuffer> {
	SerializingForwarderFactory {
//	 SerializingForwarder lastSendTrapper;

//	@Override
//	public SendTrapper<Object, ByteBuffer> createSendTrapper(InputPort anInputPort,
//			NamingSender<ByteBuffer> aDestination) {
//		lastSendTrapper = new ASerializingForwarder(anInputPort, aDestination);
////		return new ASerializingForwarder(anInputPort, aDestination);
//		return lastSendTrapper;
//	}
	@Override
	public SerializingForwarder createSendTrapper(InputPort anInputPort,
			NamingSender<ByteBuffer> aDestination) {
//		lastSendTrapper = new ASerializingForwarder(anInputPort, aDestination);
		return new ASerializingForwarder(anInputPort, aDestination);
//		return lastSendTrapper;
	}


	/*
	 * subclasses need not implement the getLastSendTrapper() method, it is not in the interface
	 */
//	public SendTrapper<Object, ByteBuffer> getLastSendTrapper() {
//		return lastSendTrapper;
//	}

}
