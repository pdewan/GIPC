package port.jitter;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;

public class AJitterControllingTrapperFactory implements TrapperFactory{
	public AJitterControllingTrapperFactory() {
		
	}

	@Override
	public ReceiveTrapper createReceiveTrapper(InputPort anInputPort,
			ReceiveNotifier aReceiveNotifier) {
		return new AJitterControllingReceiveTrapper(anInputPort, aReceiveNotifier);
	}

	@Override
	public SendTrapper createSendTrapper(InputPort anInputPort,
			NamingSender aDestination) {
		return new ATimeStampingSendTrapper(aDestination);
	}
	

}
