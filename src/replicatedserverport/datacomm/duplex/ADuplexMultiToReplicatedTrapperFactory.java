package replicatedserverport.datacomm.duplex;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.group.GroupSender;
import inputport.datacomm.simplex.SenderQueryable;
import replicatedserverport.datacomm.simplex.AMultiToReplicatedTrapperFactory;
import replicatedserverport.datacomm.simplex.MultiToReplicatedSendTrapper;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperFactory;
// converts physical server to logical
public class ADuplexMultiToReplicatedTrapperFactory<MessageType> 
	extends AMultiToReplicatedTrapperFactory<MessageType>
	implements MultiToReplicatedTrapperFactory<MessageType, MessageType>{
//	LastSenderQueryable lastSenderQueryable;
	public ADuplexMultiToReplicatedTrapperFactory() {
//		lastSenderQueryable = new ALastSenderQueryable();
	}

	@Override
	public ReceiveTrapper<MessageType, MessageType> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<MessageType> receiveNotifier) {
//		return new AnAllAcceptingMultiToReplicatedReceiveTrapper<MessageType>(anInputPort, receiveNotifier, lastSenderQueryable);
		return new AnAllAcceptingMultiToReplicatedReceiveTrapper<MessageType>(anInputPort, receiveNotifier);

	}

//	@Override
//	public MultiToReplicatedSendTrapper<MessageType, MessageType> createUniToGroupSendTrapper(
//			InputPort anInputPort, GroupSender<MessageType> aDestination) {
//		return new ADuplexMultiToReplicatedSendForwarder<MessageType>(anInputPort, aDestination, lastSenderQueryable);
//	}

}
