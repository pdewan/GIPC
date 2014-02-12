package extraip;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;

public class AMultiToUniReceiveForwarderFactory<MessageType> implements ReceiveTrapperFactory<MessageType, MessageType>{

	@Override
	public ReceiveTrapper<MessageType, MessageType> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<MessageType> receiveNotifier) {
		return new AMultiToUniReceiveForwarder<MessageType>(anInputPort, receiveNotifier);
	}

}
