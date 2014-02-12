package extraip;

import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.simplex.object.ObjectReceiveTrapperFactory;

public class AReceiveObjectForwarderFactory implements ObjectReceiveTrapperFactory {
	@Override
	public ReceiveNotifier<Object> createObjectReceiveTrapper(
			ReceiveNotifier receiveRegistrarAndNotifier) {
		return new AReceiveObjectForwarder(receiveRegistrarAndNotifier);
	}
}
