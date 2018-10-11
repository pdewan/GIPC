package extraip;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;
import inputport.datacomm.simplex.buffer.AClientChannelSendBufferForwarder;
import inputport.datacomm.simplex.buffer.ClientChannelBufferSendTrapperFactory;
import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import port.causal.AVectorTimeStamp;
import port.causal.VectorTimeStamp;



public class ACausalClientChannelBufferFactory<ChannelType> implements
	ClientChannelBufferSendTrapperFactory<ChannelType>,
	ReceiveTrapperFactory<ByteBuffer, ByteBuffer>{
	VectorTimeStamp channelVectorTimeStamp = new AVectorTimeStamp();
	// can be overridden by, for instance, a delaying forwarder
	NamingSender<ByteBuffer> createSendForwarder(InputPort anInputPort, SimplexBufferClientInputPortDriver<ChannelType> clientInputDriver) {
		return new AClientChannelSendBufferForwarder<ChannelType>(anInputPort, clientInputDriver);

	}
	@Override
	public NamingSender<ByteBuffer> createClientChannelBufferSendTrapper(
			InputPort anInputPort, SimplexBufferClientInputPortDriver<ChannelType> clientInputDriver) {
		NamingSender<ByteBuffer> forwarder = createSendForwarder (anInputPort, clientInputDriver);
//		return new ACausalSendMessageForwarder(forwarder, channelVectorTimeStamp, anInputPort.getLocalName());
		return null;
	}

	@Override
	public ReceiveTrapper<ByteBuffer, ByteBuffer> createReceiveTrapper(
			InputPort anInputPort,
			ReceiveNotifier<ByteBuffer> receiveNotifier) {
//		return new ACausalReceiveMessageForwarder<ByteBuffer, ByteBuffer>(anInputPort, receiveNotifier, channelVectorTimeStamp);
		return null;
	}

}
