package replicatedserverport.datacomm.simplex;

import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.NamingReplier;
import inputport.datacomm.group.GroupSender;

public interface MultiToReplicatedSendTrapper<InMessageType, OutMessageType> extends SendTrapper<InMessageType, OutMessageType>,
	NamingSender<InMessageType>, NamingReplier<InMessageType> {
	GroupSender<OutMessageType> getDestination();
	void setDestination (GroupSender<OutMessageType> newVal);	

}
