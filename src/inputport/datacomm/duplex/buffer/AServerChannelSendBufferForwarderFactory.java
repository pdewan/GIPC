package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.NamingSender;

public class AServerChannelSendBufferForwarderFactory<RequestChannelType, ChannelType> implements ServerChannelBufferSendTrapperFactory<RequestChannelType, ChannelType> {

	@Override
	public NamingSender<ByteBuffer> createServerChannelBufferSendTrapper(
			DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType> aBufferDuplexServerInputPort,
			DuplexServerInputPortDriver<RequestChannelType, ChannelType> aDuplexServerInputDriver) {
		return new AServerChannelSendBufferForwarder (aDuplexServerInputDriver, aBufferDuplexServerInputPort);
	}

}
