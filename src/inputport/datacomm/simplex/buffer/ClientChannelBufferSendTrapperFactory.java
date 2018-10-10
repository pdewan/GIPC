package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;

public interface ClientChannelBufferSendTrapperFactory<ChannelType> {
	NamingSender<ByteBuffer> createClientChannelBufferSendTrapper(
			InputPort aSource,
			SimplexBufferClientInputPortDriver<ChannelType> aClientInputDriver);

}
