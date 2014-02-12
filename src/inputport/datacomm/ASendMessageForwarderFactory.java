package inputport.datacomm;
import inputport.InputPort;
public class ASendMessageForwarderFactory<InAndOutMessageType> implements SendTrapperFactory<InAndOutMessageType, InAndOutMessageType> {

	@Override
	public SendTrapper<InAndOutMessageType, InAndOutMessageType> createSendTrapper(			
			InputPort anInputPort,
			NamingSender<InAndOutMessageType> aDestination) {		
		return new ASendMessageForwarder (aDestination);
	}
}
