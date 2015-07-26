package replicatedserverport.rpc.duplex.fixedresponse;

import inputport.InputPort;
import inputport.datacomm.AReceiveMessageForwarder;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;

public class AFixedResponseServerDuplexTrapperFactory 
	implements TrapperFactory<Object, Object>{
	SendTrapper<Object, Object> lastSendTrapper;
	
	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> destination) {
		lastSendTrapper = new AFixedResponseServerDuplexSendTrapper(anInputPort, destination);
//		return new AFixedResponseServerDuplexSendTrapper(anInputPort, destination);
		return lastSendTrapper;
		
	}

	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> receiveNotifier) {
		return new AReceiveMessageForwarder(anInputPort, receiveNotifier );
	}

	public SendTrapper<Object, Object> getLastSendTrapper() {
		// TODO Auto-generated method stub
		return lastSendTrapper;
	}
}
