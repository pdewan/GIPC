package port.causal;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupTrapperFactory;

// new factory must be created each pair of send and receive trappers
public class ACausalClientObjectMessageFactory implements
	GroupTrapperFactory<Object, Object> {
	VectorTimeStamp vectorTimeStamp = new AVectorTimeStamp();
	@Override
	public GroupSendTrapper<Object, Object> createGroupSendTrapper(InputPort anInputPort,
			GroupNamingSender<Object> aDestination) {
		
		return new ACausalSendMessageForwarder(aDestination, vectorTimeStamp, anInputPort.getLocalName());
	}
	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(InputPort anInputPort,
			ReceiveNotifier<Object> aReceiveNotifier) {
		return new ACausalReceiveMessageForwarder(anInputPort, aReceiveNotifier, vectorTimeStamp);

	}

}
