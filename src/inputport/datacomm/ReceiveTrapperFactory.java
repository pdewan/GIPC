package inputport.datacomm;

import inputport.InputPort;


public interface ReceiveTrapperFactory<InMessageType, OutMessageType> {
	ReceiveTrapper<InMessageType, OutMessageType> createReceiveTrapper(InputPort anInputPort, ReceiveNotifier<OutMessageType> aReceiveNotifier) ;
}
