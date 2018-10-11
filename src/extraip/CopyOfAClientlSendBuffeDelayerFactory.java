package extraip;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.buffer.AClientChannelSendBufferForwarder;
import inputport.datacomm.simplex.buffer.ClientChannelBufferSendTrapperFactory;
import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import port.delay.AServerSendMessageDelayingTrapper;


public class CopyOfAClientlSendBuffeDelayerFactory<ChannelType> implements ClientChannelBufferSendTrapperFactory<ChannelType>{

	@Override
	public NamingSender<ByteBuffer> createClientChannelBufferSendTrapper(InputPort anInputPort,
			SimplexBufferClientInputPortDriver<ChannelType> clientInputDriver) {
		NamingSender<ByteBuffer> forwarder = new AClientChannelSendBufferForwarder<ChannelType>(anInputPort, clientInputDriver);
		return new AServerSendMessageDelayingTrapper(anInputPort, forwarder);
	}

}
