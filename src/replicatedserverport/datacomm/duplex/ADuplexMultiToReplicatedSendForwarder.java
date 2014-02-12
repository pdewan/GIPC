package replicatedserverport.datacomm.duplex;

import inputport.InputPort;
import inputport.datacomm.group.GroupSender;
import inputport.datacomm.simplex.SenderQueryable;
import replicatedserverport.datacomm.simplex.AMultiToReplicatedSendForwarder;

public class ADuplexMultiToReplicatedSendForwarder<MessageType> 
			extends AMultiToReplicatedSendForwarder<MessageType> {
	SenderQueryable lastSenderQueryable;

	public ADuplexMultiToReplicatedSendForwarder(InputPort anInputPort,
			GroupSender<MessageType> aDestination, SenderQueryable aLastSenderQueryable) {
		super(anInputPort, aDestination);
		lastSenderQueryable = aLastSenderQueryable;
	}
//	public void reply(String aSource, MessageType aMessage) {
//		destination.send(lastSenderQueryable.getLastSender(), aMessage);
//	}

}
