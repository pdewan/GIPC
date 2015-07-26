package inputport.datacomm;
import java.nio.ByteBuffer;

import inputport.InputPort;
public class ASendMessageForwarderFactory<InAndOutMessageType> implements SendTrapperFactory<InAndOutMessageType, InAndOutMessageType> {
	 SendTrapper<InAndOutMessageType, InAndOutMessageType> lastTrapper;

	@Override
	public SendTrapper<InAndOutMessageType, InAndOutMessageType> createSendTrapper(			
			InputPort anInputPort,
			NamingSender<InAndOutMessageType> aDestination) {	
		lastTrapper = new ASendMessageForwarder (aDestination);
//		return new ASendMessageForwarder (aDestination);
		return lastTrapper;
	}

	
	public SendTrapper<InAndOutMessageType, InAndOutMessageType> getLastSendTrapper() {
		return lastTrapper;
	}

	
}
