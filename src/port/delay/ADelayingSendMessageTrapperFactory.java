package port.delay;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.SendTrapperFactory;

public class ADelayingSendMessageTrapperFactory<MessageType> implements SendTrapperFactory<MessageType, MessageType>{
	public ADelayingSendMessageTrapperFactory() {
		
	}
	@Override
	public SendTrapper<MessageType, MessageType> createSendTrapper(
			InputPort anInputPort, NamingSender<MessageType> aDestination) {
		// TODO Auto-generated method stub
		return new ADelayingSendMessageTrapper(anInputPort, aDestination);
	}

//	@Override
//	public UniNamingSender<ByteBuffer> createClientChannelBufferSendTrapper(InputPort anInputPort,
//			ClientInputPortDriver<ChannelType> clientInputDriver) {
//		UniNamingSender<ByteBuffer> forwarder = new AClientChannelSendBufferForwarder<ChannelType>(anInputPort, clientInputDriver);
//		return new ASendMessageDelayingForwarder(anInputPort, forwarder);
//	}

}
