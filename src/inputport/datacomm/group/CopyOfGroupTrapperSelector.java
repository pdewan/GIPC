package inputport.datacomm.group;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;

public interface CopyOfGroupTrapperSelector<SendInMessageType, SendOutMessageType> {
	ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> getReceiveTrapperFactory();

	void setReceiveTrapperFactory(
			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory);
	
	ReceiveTrapper<SendOutMessageType, SendInMessageType> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<SendInMessageType> aDestination);

	GroupSendTrapperFactory getGroupSendTrapperFactory();

	void setGroupSendTrapperFactory(
			GroupSendTrapperFactory<SendInMessageType, SendOutMessageType> aSendTrapperFactory);

	GroupSendTrapper<SendInMessageType, SendOutMessageType> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<SendOutMessageType> aDestination);
	

}