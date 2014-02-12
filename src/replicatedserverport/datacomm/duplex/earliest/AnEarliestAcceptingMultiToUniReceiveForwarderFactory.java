package replicatedserverport.datacomm.duplex.earliest;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;

public class AnEarliestAcceptingMultiToUniReceiveForwarderFactory<MessageType> implements ReceiveTrapperFactory<MessageType, MessageType>{
	public AnEarliestAcceptingMultiToUniReceiveForwarderFactory() {
		
	}

	@Override
	public ReceiveTrapper<MessageType, MessageType> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<MessageType> receiveNotifier) {
		return new AnEarliestAcceptingMultiToUniReceiveForwarder<MessageType>(anInputPort, receiveNotifier);
	}

}
