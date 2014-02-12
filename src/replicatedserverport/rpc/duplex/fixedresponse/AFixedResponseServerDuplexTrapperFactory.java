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
	
	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> destination) {
		return new AFixedResponseServerDuplexSendTrapper(anInputPort, destination);
		
	}

	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> receiveNotifier) {
		return new AReceiveMessageForwarder(anInputPort, receiveNotifier );
	}
}
