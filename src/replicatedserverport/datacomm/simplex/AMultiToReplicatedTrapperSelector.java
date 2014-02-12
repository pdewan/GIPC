package replicatedserverport.datacomm.simplex;

import inputport.InputPort;
import inputport.datacomm.AReceiveTrapperSelector;
import inputport.datacomm.group.GroupSender;

public class AMultiToReplicatedTrapperSelector<SendInMessageType, SendOutMessageType> 
    extends AReceiveTrapperSelector<SendInMessageType, SendOutMessageType>
	implements 	MultiToReplicatedTrapperSelector<SendInMessageType, SendOutMessageType>{
	MultiToReplicatedTrapperFactory<SendInMessageType, SendOutMessageType>  sendFactory = new AMultiToReplicatedTrapperFactory();
//	List<ReceiveTrapperFactory<SendOutMessageType, SendInMessageType>>  receiveFactories = new ArrayList();

//	ReceiveTrapperFactory<SendOutMessageType, SendInMessageType>  receiveFactory =
//			new AReceiveMessageForwarderFactory();
	boolean usingReceveDefault = true; // assuming default is forwarder

	public AMultiToReplicatedTrapperSelector() {
//		receiveFactories.add(new AReceiveMessageForwarderFactory());
	}
	@Override
	public MultiToReplicatedSendTrapper<SendInMessageType, SendOutMessageType> createUniToAllSendTrapper(
			InputPort anInputPort,
			GroupSender<SendOutMessageType> aDestination) {
		return sendFactory.createUniToGroupSendTrapper(anInputPort, aDestination);
	}

	@Override
	public MultiToReplicatedTrapperFactory getUniToAllSendTrapperFactory() {
		return sendFactory;
	}

	@Override
	public void setUniToGroupSendTrapperFactory(
			MultiToReplicatedTrapperFactory<SendInMessageType, SendOutMessageType> aSendTrapperFactory) {
		sendFactory = aSendTrapperFactory;
	}

//	@Override
//	public ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> getReceiveTrapperFactory() {
//		return receiveFactories.get(0); // the first trapper in backwards chain
//	}
//	
//	@Override
//	public void setReceiveTrapperFactory(
//			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
//		receiveFactories.set(0, aReceiveTrapperFactory);
//		usingReceveDefault = false;
//	}
//	@Override
//	public void addReceiveTrapperFactory(
//			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
//		if (usingReceveDefault)
//			setReceiveTrapperFactory(aReceiveTrapperFactory);
//		else			
//			receiveFactories.add (aReceiveTrapperFactory);
//		
//	}
//
//	@Override
//	public void addReceiveTrapperFactory(
//			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
//		
//	}
	
}
