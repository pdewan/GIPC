package inputport.datacomm.group;

import inputport.InputPort;
import inputport.datacomm.NamingSender;

public class AGroupToUniSendTrapperSelector<SendInMessageType, SendOutMessageType> implements 
	GroupToUniSendTrapperSelector<SendInMessageType, SendOutMessageType>{
	GroupToUniSendTrapperFactory<SendInMessageType, SendOutMessageType>  factory = 	new AGroupToUniSendMessageForwarderFactory();
	
	@Override
	public GroupToUniSendTrapper<SendInMessageType, SendOutMessageType> createGroupToUniSendTrapper(
			InputPort anInputPort, NamingSender<SendOutMessageType> aDestination) {
		return factory.createGroupToUniSendTrapper(anInputPort, aDestination);
	}

	@Override
	public GroupToUniSendTrapperFactory getGroupToUniSendTrapperFactory() {
		return factory;
	}

	@Override
	public void setGroupToUniSendTrapperFactory(
			GroupToUniSendTrapperFactory<SendInMessageType, SendOutMessageType> sendTrapperFactory) {
		factory = sendTrapperFactory;
	}


}
