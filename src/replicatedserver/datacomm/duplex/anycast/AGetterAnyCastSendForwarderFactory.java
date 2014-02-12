package replicatedserver.datacomm.duplex.anycast;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.group.GroupSender;
import replicatedserverport.datacomm.simplex.MultiToReplicatedSendTrapper;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperFactory;

public class AGetterAnyCastSendForwarderFactory<InMessageType> 
	implements MultiToReplicatedTrapperFactory<InMessageType, InMessageType> {

	@Override
	public MultiToReplicatedSendTrapper<InMessageType, InMessageType> createUniToGroupSendTrapper(
			InputPort anInputPort, GroupSender<InMessageType> aDestination) {
		return new AGetterAnyCastSendForwarder<InMessageType>(anInputPort, aDestination);
	}

	@Override
	public ReceiveTrapper<InMessageType, InMessageType> createReceiveTrapper(
			InputPort anInputPort,
			ReceiveNotifier<InMessageType> aReceiveNotifier) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
