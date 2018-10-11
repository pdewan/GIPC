package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;

public class AClientChannelSendBufferForwarderFactory<ChannelType> implements ClientChannelBufferSendTrapperFactory<ChannelType> {

	@Override
	public NamingSender<ByteBuffer> createClientChannelBufferSendTrapper(InputPort anInputPort, SimplexBufferClientInputPortDriver<ChannelType> aClientInputDriver) {
		return new AClientChannelSendBufferForwarder<ChannelType>(anInputPort, aClientInputDriver);
	}

	

}
