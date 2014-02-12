package replicatedserverport.rpc.simplex;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.rpc.simplex.ASimplexRPCClientInputPort;



public class AReplicatedServerRPCClientPort extends ASimplexRPCClientInputPort {
	public AReplicatedServerRPCClientPort(SimplexClientInputPort<Object> aTypedClientInputPort) {
		super(aTypedClientInputPort);		
	}

}
