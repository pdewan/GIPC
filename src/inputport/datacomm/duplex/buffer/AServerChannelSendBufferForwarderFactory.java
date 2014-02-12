package inputport.datacomm.duplex.buffer;

import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

public class AServerChannelSendBufferForwarderFactory<RequestChannelType, ChannelType> implements ServerChannelBufferSendTrapperFactory<RequestChannelType, ChannelType> {

	@Override
	public NamingSender<ByteBuffer> createServerChannelBufferSendTrapper(
			DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType> aBufferDuplexServerInputPort,
			DuplexServerInputPortDriver<RequestChannelType, ChannelType> aDuplexServerInputDriver) {
		return new AServerChannelSendBufferForwarder (aDuplexServerInputDriver, aBufferDuplexServerInputPort);
	}

}
