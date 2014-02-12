package extraip;

import inputport.InputPort;
import inputport.datacomm.AReceiveMessageForwarderFactory;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapperFactory;

import java.nio.ByteBuffer;

public class ServerChannelBufferReceiverTrapperSelector {
	static ReceiveTrapperFactory<ByteBuffer, ByteBuffer> receiveTrapperFactory = new AReceiveMessageForwarderFactory();
	public static ReceiveTrapperFactory<ByteBuffer, ByteBuffer> getBufferReceiveTrapperFactory() {
		return receiveTrapperFactory;
	}
	public static void setBufferReceiveTrapperFactory(
			ReceiveTrapperFactory<ByteBuffer, ByteBuffer> aReceiveTrapperFactory) {
		receiveTrapperFactory = aReceiveTrapperFactory;
	}	
	public static ReceiveNotifier<ByteBuffer> createReceiveTrapper(InputPort anInputPort, ReceiveNotifier<ByteBuffer> destination) {
		return receiveTrapperFactory.createReceiveTrapper(anInputPort, destination);
	}
}
