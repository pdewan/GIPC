package inputport.datacomm;

import inputport.InputPort;

import java.util.ArrayList;
import java.util.List;

public abstract class AReceiveTrapperSelector<SendInMessageType, SendOutMessageType> implements ReceiveTrapperSelector<SendInMessageType, SendOutMessageType> {	

	List<ReceiveTrapperFactory<SendOutMessageType, SendInMessageType>>  receiveFactories = new ArrayList();

//	List<SendTrapperFactory<SendInMessageType, SendOutMessageType>>  sendFactories = new ArrayList();
//	boolean usingSendDefault = true; // assuming default is forwarder
	boolean usingReceveDefault = true; // assuming default is forwarder
	public AReceiveTrapperSelector() {
		receiveFactories.add(new AReceiveMessageForwarderFactory());
//		sendFactories.add(new ASendMessageForwarderFactory());		
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
		else {			
//			receiveFactories.add (aReceiveTrapperFactory);
			ATrapperSelector.addIfObjectofDifferentClass(receiveFactories, aReceiveTrapperFactory);
		}
		
	}
//	
//	@Override
//	public SendTrapper<SendInMessageType, SendOutMessageType> createSendTrapper(
//			InputPort anInputPort, NamingSender<SendOutMessageType> aDestination) {
//		
//		SendTrapper<SendInMessageType, SendOutMessageType> retVal = null;
//		for (SendTrapperFactory<SendInMessageType, SendOutMessageType>  aFactory:sendFactories) {
////			GroupSendTrapper<SendInMessageType, SendOutMessageType> currentTrapper = aFactory.createGroupSendTrapper(anInputPort, aDestination);
//			if (retVal == null ){// simply get rid of the forwarder
//				retVal = aFactory.createSendTrapper(anInputPort, aDestination);
//			} else {
//				// trappers chained forward in reverse factory order
//				retVal = aFactory.createSendTrapper(anInputPort, (NamingSender<SendOutMessageType>) retVal);
////				currentTrapper.setDestination((GroupNamingSender<SendOutMessageType>)retVal); // normally in and out type will be matched
////				retVal = currentTrapper;
//			}
//			
//		}
//		return retVal;
//	}
//
//	@Override
//	public SendTrapperFactory getSendTrapperFactory() {
//		return sendFactories.get(sendFactories.size() - 1); // return first trapper in forward chain
//	}
//
//	@Override
//	public void setSendTrapperFactory(
//			SendTrapperFactory<SendInMessageType, SendOutMessageType> sendTrapperFactory) {
//		sendFactories.set(sendFactories.size() - 1, sendTrapperFactory); // set first trapper in forward chain
//		usingSendDefault = false;
//	}	
//	@Override
//	public void addSendTrapperFactory(
//			SendTrapperFactory<SendInMessageType, SendOutMessageType> sendTrapperFactory) {
//		if (usingSendDefault) 
//			setSendTrapperFactory(sendTrapperFactory);
//		else
//			sendFactories.add (sendTrapperFactory); 
//	}
//	@Override
//	public ReceiveTrapper<SendOutMessageType, SendInMessageType> createReceiveTrapper(
//			InputPort anInputPort,
//			ReceiveNotifier<SendInMessageType> aDestination) {
//		return receiveTrapperFactory.createReceiveTrapper(anInputPort, aDestination);
//	}
//	@Override
//	public SendTrapper<SendInMessageType, SendOutMessageType> createSendTrapper(
//			InputPort anInputPort,
//			NamingSender<SendOutMessageType> aDestination) {
//		return sendTrapperFactory.createSendTrapper(anInputPort, aDestination);
//	}
//	@Override
//	public ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> getReceiveTrapperFactory() {
//		return receiveTrapperFactory;
//	}
//	@Override
//	public SendTrapperFactory getSendTrapperFactory() {
//		return sendTrapperFactory;
//	}
//	@Override
//	public void setReceiveTrapperFactory(
//			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
//		receiveTrapperFactory = aReceiveTrapperFactory;
//	}
//	@Override
//	public void setSendTrapperFactory(
//			SendTrapperFactory<SendInMessageType, SendOutMessageType> aSendTrapperFactory) {
//		sendTrapperFactory = aSendTrapperFactory;
//	}	


	
	
}
