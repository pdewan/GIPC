package inputport.rpc.simplex;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.SendTrapperFactory;

public class ASimplexCallSendTrapperFactory implements SendTrapperFactory<Object, Object> {

	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {
		return new ASimplexCallSendTrapper(anInputPort, aDestination);
	}

}
