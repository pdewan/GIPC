package inputport.rpc.simplex;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.SendTrapperFactory;

public class ASimplexCallSendTrapperFactory implements SendTrapperFactory<Object, Object> {
	SendTrapper<Object, Object> lastSendTrapper;
	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {
		lastSendTrapper = new ASimplexCallSendTrapper(anInputPort, aDestination);
//		return new ASimplexCallSendTrapper(anInputPort, aDestination);
		return lastSendTrapper;

	}
	public SendTrapper<Object, Object> getLastSendTrapper() {
		return lastSendTrapper;
	}

}
