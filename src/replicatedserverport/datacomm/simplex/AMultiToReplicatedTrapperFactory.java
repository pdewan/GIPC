package replicatedserverport.datacomm.simplex;

import inputport.InputPort;
import inputport.datacomm.AReceiveMessageForwarder;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;
import inputport.datacomm.group.GroupSender;

public class AMultiToReplicatedTrapperFactory<InMessageType> 
	implements MultiToReplicatedTrapperFactory<InMessageType, InMessageType>
	     {
	public AMultiToReplicatedTrapperFactory() {
		
	}

	@Override
	public MultiToReplicatedSendTrapper<InMessageType, InMessageType> createUniToGroupSendTrapper(
			InputPort anInputPort, GroupSender<InMessageType> aDestination) {
		return new AMultiToReplicatedSendForwarder(anInputPort, aDestination);
	}

	@Override
	public ReceiveTrapper<InMessageType, InMessageType> createReceiveTrapper(
			InputPort anInputPort,
			ReceiveNotifier<InMessageType> aReceiveNotifier) {
		return new AReceiveMessageForwarder(anInputPort, aReceiveNotifier);
	}
	

	

}
