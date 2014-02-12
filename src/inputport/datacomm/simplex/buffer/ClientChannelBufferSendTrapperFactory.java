package inputport.datacomm.simplex.buffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

public interface ClientChannelBufferSendTrapperFactory<ChannelType> {
	NamingSender<ByteBuffer> createClientChannelBufferSendTrapper(
			InputPort aSource,
			SimplexBufferClientInputPortDriver<ChannelType> aClientInputDriver);

}
