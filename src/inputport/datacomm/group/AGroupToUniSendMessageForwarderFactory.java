package inputport.datacomm.group;

import inputport.InputPort;
import inputport.datacomm.NamingSender;

public class AGroupToUniSendMessageForwarderFactory<InMessageType> implements GroupToUniSendTrapperFactory<InMessageType, InMessageType> {
	@Override
	public GroupToUniSendTrapper<InMessageType, InMessageType> createGroupToUniSendTrapper(
			InputPort anInputPort, NamingSender<InMessageType> aDestination) {
		return new AGroupToUniSendMessageForwarder (aDestination);
	}


	

}
