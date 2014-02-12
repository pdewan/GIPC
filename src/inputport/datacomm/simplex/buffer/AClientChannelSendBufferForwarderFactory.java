package inputport.datacomm.simplex.buffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

public class AClientChannelSendBufferForwarderFactory<ChannelType> implements ClientChannelBufferSendTrapperFactory<ChannelType> {

	@Override
	public NamingSender<ByteBuffer> createClientChannelBufferSendTrapper(InputPort anInputPort, SimplexBufferClientInputPortDriver<ChannelType> aClientInputDriver) {
		return new AClientChannelSendBufferForwarder<ChannelType>(anInputPort, aClientInputDriver);
	}

	

}
