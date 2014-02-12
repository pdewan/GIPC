package inputport.rpc.simplex;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.ReceiveTrapperFactory;

public class ASimplexCallReceiveTrapperFactory implements ReceiveTrapperFactory<Object, Object> {
	
	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(InputPort anInputPort,
			ReceiveNotifier<Object> aReceiveRegistrarAndNotifier) {
		return new ASimplexCallReceiveTrapper (anInputPort, aReceiveRegistrarAndNotifier);
	}

	
}
