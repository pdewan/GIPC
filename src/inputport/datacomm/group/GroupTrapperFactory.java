package inputport.datacomm.group;

import inputport.datacomm.ReceiveTrapperFactory;

public interface GroupTrapperFactory<InMessageType, OutMessageType>  extends 
	GroupSendTrapperFactory<InMessageType, OutMessageType>,
	ReceiveTrapperFactory<OutMessageType, InMessageType>{

}
