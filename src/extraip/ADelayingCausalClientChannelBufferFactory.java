package extraip;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.buffer.ClientChannelBufferSendTrapperFactory;
import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;

import java.nio.ByteBuffer;

import port.delay.ADelayingSendMessageTrapperFactory;


public class ADelayingCausalClientChannelBufferFactory<ChannelType> extends 
			 ACausalClientChannelBufferFactory<ChannelType> {
	@Override
	NamingSender<ByteBuffer> createSendForwarder(InputPort anInputPort, SimplexBufferClientInputPortDriver<ChannelType> aClientInputDriver) {
		ClientChannelBufferSendTrapperFactory<ChannelType> factory =
			(ClientChannelBufferSendTrapperFactory<ChannelType>) new ADelayingSendMessageTrapperFactory<ChannelType>();
		return  factory.createClientChannelBufferSendTrapper(anInputPort, aClientInputDriver);

	}

}
