package inputport.datacomm;

import inputport.InputPort;

public class CopyOfATrapperSelector<SendInMessageType, SendOutMessageType> implements CopyOfTrapperSelector<SendInMessageType, SendOutMessageType> {	
	SendTrapperFactory<SendInMessageType, SendOutMessageType>  sendTrapperFactory = new ASendMessageForwarderFactory();	
	ReceiveTrapperFactory<SendOutMessageType, SendInMessageType>  receiveTrapperFactory = new AReceiveMessageForwarderFactory();
	@Override
	public ReceiveTrapper<SendOutMessageType, SendInMessageType> createReceiveTrapper(
			InputPort anInputPort,
			ReceiveNotifier<SendInMessageType> aDestination) {
		return receiveTrapperFactory.createReceiveTrapper(anInputPort, aDestination);
	}
	@Override
	public SendTrapper<SendInMessageType, SendOutMessageType> createSendTrapper(
			InputPort anInputPort,
			NamingSender<SendOutMessageType> aDestination) {
		return sendTrapperFactory.createSendTrapper(anInputPort, aDestination);
	}
	@Override
	public ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> getReceiveTrapperFactory() {
		return receiveTrapperFactory;
	}
	@Override
	public SendTrapperFactory getSendTrapperFactory() {
		return sendTrapperFactory;
	}
	@Override
	public void setReceiveTrapperFactory(
			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
		receiveTrapperFactory = aReceiveTrapperFactory;
	}
	@Override
	public void setSendTrapperFactory(
			SendTrapperFactory<SendInMessageType, SendOutMessageType> aSendTrapperFactory) {
		sendTrapperFactory = aSendTrapperFactory;
	}	

//	public  ReceiveTrapperFactory getReceiveTrapperFactory() {
//		return receiveTrapperFactory;
//	}
//	
//	public  void setReceiveTrapperFactory(
//			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory) {
//		receiveTrapperFactory = aReceiveTrapperFactory;
//	}
//	
//	public  SendTrapperFactory getSendTrapperFactory() {
//		return sendTrapperFactory;
//	}
//	
//	public  void setSendTrapperFactory(
//			SendTrapperFactory aSenderTrapperFactory) {
//		sendTrapperFactory = aSenderTrapperFactory;
//	}
//	
//	public  UniNamingSender<SendOutMessageType, SendInMessageType> createSendTrapper(InputPort anInputPort, 
//			UniNamingSender<Object> aDestination) {
//		return sendTrapperFactory.createSendTrapper(anInputPort, aDestination);
//	}
//	
//	public  ReceiveNotifier<SendOutMessageType, SendInMessageType> createReceiveTrapper(InputPort anInputPort,
//			ReceiveNotifier<Object> aDestination) {
//		return receiveTrapperFactory.createReceiveTrapper(anInputPort, aDestination);
//	}
	
	
}
