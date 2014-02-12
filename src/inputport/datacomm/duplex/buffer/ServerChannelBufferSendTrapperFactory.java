package inputport.datacomm.duplex.buffer;

import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

public interface ServerChannelBufferSendTrapperFactory<RequestChannelType, ChannelType> {
	NamingSender<ByteBuffer> createServerChannelBufferSendTrapper(DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType> aBufferDuplexServerInputPort,
			DuplexServerInputPortDriver<RequestChannelType, ChannelType> aDuplexServerInputDriver);

}
