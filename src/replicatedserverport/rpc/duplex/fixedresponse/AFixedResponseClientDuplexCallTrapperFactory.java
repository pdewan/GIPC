package replicatedserverport.rpc.duplex.fixedresponse;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;
import inputport.rpc.duplex.ADuplexCallTrapperFactory;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseClientDuplexCallSendTrapper;

// a new instance must be created for each send and receive trapper
public class AFixedResponseClientDuplexCallTrapperFactory extends ADuplexCallTrapperFactory
	implements TrapperFactory<Object, Object> {

	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {
		return new ASingleResponseClientDuplexCallSendTrapper(anInputPort, aDestination);

		
	}

	
}
