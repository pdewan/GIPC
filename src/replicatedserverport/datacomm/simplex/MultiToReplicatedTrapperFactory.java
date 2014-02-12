package replicatedserverport.datacomm.simplex;


import inputport.InputPort;
import inputport.datacomm.ReceiveTrapperFactory;
import inputport.datacomm.group.GroupSender;

public interface MultiToReplicatedTrapperFactory<InMessageType, OutMessageType>  extends ReceiveTrapperFactory<InMessageType, InMessageType> {
	MultiToReplicatedSendTrapper<InMessageType, OutMessageType> createUniToGroupSendTrapper(
			InputPort anInputPort, 
			GroupSender<OutMessageType>  aDestination);

}
