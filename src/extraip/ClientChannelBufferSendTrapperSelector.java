package extraip;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.buffer.AClientChannelSendBufferForwarderFactory;
import inputport.datacomm.simplex.buffer.ClientChannelBufferSendTrapperFactory;
import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;

import java.nio.ByteBuffer;

public class ClientChannelBufferSendTrapperSelector {
	static ClientChannelBufferSendTrapperFactory clientChannelBufferSendTrapperFactory = new AClientChannelSendBufferForwarderFactory();
	public static ClientChannelBufferSendTrapperFactory getClientChannelBufferSendTrapperFactory() {
		return clientChannelBufferSendTrapperFactory;
	}
	public static void setClientChannelBufferSendTrapperFactory(
			ClientChannelBufferSendTrapperFactory clientChannelBufferSendTrapperFactory) {
		ClientChannelBufferSendTrapperSelector.clientChannelBufferSendTrapperFactory = clientChannelBufferSendTrapperFactory;
	}
	static NamingSender<ByteBuffer> createClientChannelBufferSendTrapper(InputPort anInputPort, SimplexBufferClientInputPortDriver aDestination) {
		return clientChannelBufferSendTrapperFactory.createClientChannelBufferSendTrapper(anInputPort, aDestination);
	}

}
