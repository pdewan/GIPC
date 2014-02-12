package replicatedserverport.datacomm.simplex;

import inputport.InputPort;
import inputport.datacomm.group.GroupSender;

public class AMultiToReplicatedSendForwarder<MessageType> extends AnAbstractMultiToReplicatedSendTrapper<MessageType, MessageType> {
	public AMultiToReplicatedSendForwarder(InputPort anInputPort, GroupSender<MessageType> aDestination) {
		super (aDestination);
	}
	

	@Override
	public void send(String remoteName, MessageType message) {
		destination.sendAll(message);
	}
	
}
