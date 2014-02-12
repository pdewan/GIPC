package replicatedserverport.datacomm.simplex;

import inputport.InputPort;
import inputport.datacomm.ReceiveTrapperSelector;
import inputport.datacomm.group.GroupSender;

public interface MultiToReplicatedTrapperSelector<SendInMessageType, SendOutMessageType> extends 
ReceiveTrapperSelector<SendInMessageType, SendOutMessageType> {
	
	MultiToReplicatedTrapperFactory getUniToAllSendTrapperFactory();

	void setUniToGroupSendTrapperFactory(
			MultiToReplicatedTrapperFactory<SendInMessageType, SendOutMessageType> aSendTrapperFactory);

	MultiToReplicatedSendTrapper<SendInMessageType, SendOutMessageType> createUniToAllSendTrapper(
			InputPort anInputPort, GroupSender<SendOutMessageType> aDestination);
	

}