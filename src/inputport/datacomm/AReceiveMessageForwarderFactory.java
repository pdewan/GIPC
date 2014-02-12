package inputport.datacomm;

import inputport.InputPort;


public class AReceiveMessageForwarderFactory<InAndOutMessageType>  implements ReceiveTrapperFactory<InAndOutMessageType, InAndOutMessageType> {

	@Override
	public ReceiveTrapper<InAndOutMessageType, InAndOutMessageType> createReceiveTrapper(InputPort anInputPort, ReceiveNotifier<InAndOutMessageType> aReceiveNotifier) {
		return new AReceiveMessageForwarder(anInputPort, aReceiveNotifier);
	}

}
