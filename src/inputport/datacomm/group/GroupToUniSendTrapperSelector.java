package inputport.datacomm.group;

import inputport.InputPort;
import inputport.datacomm.NamingSender;

public interface GroupToUniSendTrapperSelector<SendInMessageType, SendOutMessageType> {
	
	GroupToUniSendTrapperFactory getGroupToUniSendTrapperFactory();

	void setGroupToUniSendTrapperFactory(
			GroupToUniSendTrapperFactory<SendInMessageType, SendOutMessageType> aSendTrapperFactory);

	GroupToUniSendTrapper<SendInMessageType, SendOutMessageType> createGroupToUniSendTrapper(
			InputPort anInputPort, NamingSender<SendOutMessageType> aDestination);
	

}