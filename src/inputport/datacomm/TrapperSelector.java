package inputport.datacomm;


public interface TrapperSelector<SendInMessageType, SendOutMessageType>
extends ReceiveTrapperSelector<SendInMessageType, SendOutMessageType>,
		SendTrapperSelector<SendInMessageType, SendOutMessageType>{

//	ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> getReceiveTrapperFactory();
//
//	void setReceiveTrapperFactory(
//			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory);
//
//	SendTrapperFactory<SendInMessageType, SendOutMessageType> getSendTrapperFactory();
//
//	void setSendTrapperFactory(
//			SendTrapperFactory<SendInMessageType, SendOutMessageType> aSendTrapperFactory);
//
//	SendTrapper<SendInMessageType, SendOutMessageType> createSendTrapper(
//			InputPort anInputPort, NamingSender<SendOutMessageType> aDestination);
//
//	ReceiveTrapper<SendOutMessageType, SendInMessageType> createReceiveTrapper(
//			InputPort anInputPort, ReceiveNotifier<SendInMessageType> aDestination);
//
//	void addReceiveTrapperFactory(
//			ReceiveTrapperFactory<SendOutMessageType, SendInMessageType> aReceiveTrapperFactory);
//
//	void addSendTrapperFactory(
//			SendTrapperFactory<SendInMessageType, SendOutMessageType> sendTrapperFactory);

}