package inputport.datacomm.group;

import inputport.InputPort;
import inputport.datacomm.AReceiveMessageForwarderFactory;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;

public class CopyOfAGroupTrapperSelector<SendInMessageType, SendOutMessageType> implements CopyOfGroupTrapperSelector<SendInMessageType, SendOutMessageType>{
	ReceiveTrapperFactory<SendOutMessageType, SendInMessageType>  receiveFactory = new AReceiveMessageForwarderFactory();

	GroupSendTrapperFactory<SendInMessageType, SendOutMessageType>  sendFactory = 	new AGroupSendMessageForwarderFactory();
	
	@Override
	public ReceiveTrapper<SendOutMessageType, SendInMessageType> createReceiveTrapper(
			InputPort anInputPort,
			ReceiveNotifier<SendInMessageType> aDestination) {
		return receiveFactory.createReceiveTrapper(anInputPort, aDestination);
	}
	@Override
	public ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> getReceiveTrapperFactory() {
		return receiveFactory;
	}
	
	@Override
	public void setReceiveTrapperFactory(
			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
		receiveFactory = aReceiveTrapperFactory;
	}
	
	@Override
	public GroupSendTrapper<SendInMessageType, SendOutMessageType> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<SendOutMessageType> aDestination) {
		return sendFactory.createGroupSendTrapper(anInputPort, aDestination);
	}

	@Override
	public GroupSendTrapperFactory getGroupSendTrapperFactory() {
		return sendFactory;
	}

	@Override
	public void setGroupSendTrapperFactory(
			GroupSendTrapperFactory<SendInMessageType, SendOutMessageType> sendTrapperFactory) {
		sendFactory = sendTrapperFactory;
	}


}
