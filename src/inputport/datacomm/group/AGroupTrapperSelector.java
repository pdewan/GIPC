package inputport.datacomm.group;

import inputport.InputPort;
import inputport.datacomm.AReceiveMessageForwarderFactory;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;

import java.util.ArrayList;
import java.util.List;

public class AGroupTrapperSelector<SendInMessageType, SendOutMessageType> implements GroupTrapperSelector<SendInMessageType, SendOutMessageType>{
	List<ReceiveTrapperFactory<SendOutMessageType, SendInMessageType>>  receiveFactories = new ArrayList();

	List<GroupSendTrapperFactory<SendInMessageType, SendOutMessageType>>  sendFactories = new ArrayList();
	
	boolean usingSendDefault = true; // assuming default is forwarder
	boolean usingReceveDefault = true; // assuming default is forwarder

	
	public AGroupTrapperSelector() {
		receiveFactories.add(new AReceiveMessageForwarderFactory());
//		sendFactories.add(new AGroupSendMessageForwarderFactory());	
		sendFactories.add(GroupSendMessageForwarderSelector.groupSendMessageForwaderFactory);	
	}
	
	@Override
	public ReceiveTrapper<SendOutMessageType, SendInMessageType> createReceiveTrapper(
			InputPort anInputPort,
			ReceiveNotifier<SendInMessageType> aDestination) {
		
		ReceiveTrapper<SendOutMessageType, SendInMessageType> retVal = null;
		for (int i = receiveFactories.size()-1; i >=0; i--) {
			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aFactory = receiveFactories.get(i);
			if (retVal == null) {
				retVal = aFactory.createReceiveTrapper(anInputPort, aDestination);
			} else {
				retVal = aFactory.createReceiveTrapper(anInputPort, (ReceiveNotifier<SendInMessageType>) retVal);
			}
		}
		return retVal;
	}
	@Override
	public ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> getReceiveTrapperFactory() {
		return receiveFactories.get(0); // the first trapper in backwards chain
	}
	
	@Override
	public void setReceiveTrapperFactory(
			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
		receiveFactories.set(0, aReceiveTrapperFactory);
		usingReceveDefault = false;
	}
	@Override
	public void addReceiveTrapperFactory(
			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
		if (usingReceveDefault)
			setReceiveTrapperFactory(aReceiveTrapperFactory);
		else			
			receiveFactories.add (aReceiveTrapperFactory);
		
	}
	
	@Override
	public GroupSendTrapper<SendInMessageType, SendOutMessageType> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<SendOutMessageType> aDestination) {
		
		GroupSendTrapper<SendInMessageType, SendOutMessageType> retVal = null;
		for (GroupSendTrapperFactory<SendInMessageType, SendOutMessageType>  aFactory:sendFactories) {
//			GroupSendTrapper<SendInMessageType, SendOutMessageType> currentTrapper = aFactory.createGroupSendTrapper(anInputPort, aDestination);
			if (retVal == null ){// simply get rid of the forwarder
				retVal = aFactory.createGroupSendTrapper(anInputPort, aDestination);
			} else {
				// trappers chained forward in reverse factory order
				retVal = aFactory.createGroupSendTrapper(anInputPort, (GroupNamingSender<SendOutMessageType>) retVal);
//				currentTrapper.setDestination((GroupNamingSender<SendOutMessageType>)retVal); // normally in and out type will be matched
//				retVal = currentTrapper;
			}
			
		}
		return retVal;
	}

	@Override
	public GroupSendTrapperFactory getGroupSendTrapperFactory() {
		return sendFactories.get(sendFactories.size() - 1); // return first trapper in forward chain
	}

	@Override
	public void setGroupSendTrapperFactory(
			GroupSendTrapperFactory<SendInMessageType, SendOutMessageType> sendTrapperFactory) {
		sendFactories.set(sendFactories.size() - 1, sendTrapperFactory); // set first trapper in forward chain
		usingSendDefault = false;
	}	
	@Override
	public void addGroupSendTrapperFactory(
			GroupSendTrapperFactory<SendInMessageType, SendOutMessageType> sendTrapperFactory) {
		if (usingSendDefault) 
			setGroupSendTrapperFactory(sendTrapperFactory);
		else
			sendFactories.add (sendTrapperFactory); 
	}



}
