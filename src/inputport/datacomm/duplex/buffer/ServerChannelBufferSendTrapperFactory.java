package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.NamingSender;

public interface ServerChannelBufferSendTrapperFactory<RequestChannelType, ChannelType> {
	NamingSender<ByteBuffer> createServerChannelBufferSendTrapper(DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType> aBufferDuplexServerInputPort,
			DuplexServerInputPortDriver<RequestChannelType, ChannelType> aDuplexServerInputDriver);

}
