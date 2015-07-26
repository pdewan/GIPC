package port.jitter;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;

public class AJitterControllingTrapperFactory implements TrapperFactory{
	SendTrapper lastSendTrapper;
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
		lastSendTrapper = new ATimeStampingSendTrapper(aDestination);
//		return new ATimeStampingSendTrapper(aDestination);
		return lastSendTrapper;
	}

	
	public SendTrapper getLastSendTrapper() {
		// TODO Auto-generated method stub
		return lastSendTrapper;
	}
	

}
