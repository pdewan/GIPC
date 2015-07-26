package port.delay;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.SendTrapperFactory;

public class ADelayingSendMessageTrapperFactory<MessageType> implements SendTrapperFactory<MessageType, MessageType>{
	 SendTrapper<MessageType, MessageType>  lastSendTrapper;
	public ADelayingSendMessageTrapperFactory() {
		
	}
	@Override
	public SendTrapper<MessageType, MessageType> createSendTrapper(
			InputPort anInputPort, NamingSender<MessageType> aDestination) {
		// TODO Auto-generated method stub
		lastSendTrapper = new ADelayingSendMessageTrapper(anInputPort, aDestination);
//		return new ADelayingSendMessageTrapper(anInputPort, aDestination);
		return lastSendTrapper;
	}
	
	public SendTrapper<MessageType, MessageType> getLastSendTrapper() {
		// TODO Auto-generated method stub
		return lastSendTrapper;
	}

//	@Override
//	public UniNamingSender<ByteBuffer> createClientChannelBufferSendTrapper(InputPort anInputPort,
//			ClientInputPortDriver<ChannelType> clientInputDriver) {
//		UniNamingSender<ByteBuffer> forwarder = new AClientChannelSendBufferForwarder<ChannelType>(anInputPort, clientInputDriver);
//		return new ASendMessageDelayingForwarder(anInputPort, forwarder);
//	}

}
