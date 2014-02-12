package inputport.datacomm.simplex.object;

import inputport.datacomm.ReceiveNotifier;

public interface ObjectReceiveTrapperFactory {
	ReceiveNotifier<Object> createObjectReceiveTrapper(ReceiveNotifier aReceiveRegistrarAndNotifier);
}
