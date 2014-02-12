package port.delay;

import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.buffer.AServerChannelSendBufferForwarder;
import inputport.datacomm.duplex.buffer.DuplexBufferGenericServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexServerInputPortDriver;
import inputport.datacomm.duplex.buffer.ServerChannelBufferSendTrapperFactory;

import java.nio.ByteBuffer;

public class AServerChannelSendBufferDelayerFactory<RequestChannelType, ChannelType> implements ServerChannelBufferSendTrapperFactory<RequestChannelType, ChannelType>{

	@Override
	public NamingSender<ByteBuffer> createServerChannelBufferSendTrapper(
			DuplexBufferGenericServerInputPort<RequestChannelType, ChannelType> bufferDuplexServerInputPort,
			DuplexServerInputPortDriver<RequestChannelType,ChannelType> duplexServerInputDriver) {
		NamingSender<ByteBuffer> forwarder = new  AServerChannelSendBufferForwarder<RequestChannelType,ChannelType>(
													duplexServerInputDriver,
													bufferDuplexServerInputPort);
//		return new AServerSendMessageDelayingTrapper(bufferDuplexServerInputPort, forwarder);
		return new ADelayingSendMessageTrapper(bufferDuplexServerInputPort, forwarder);

	}	
}
