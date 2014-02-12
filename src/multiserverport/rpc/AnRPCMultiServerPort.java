package multiserverport.rpc;

import inputport.rpc.simplex.ASimplexRPCClientInputPort;
import multiserverport.datacomm.simplex.SimplexMultiServerClientPort;


public class AnRPCMultiServerPort  extends ASimplexRPCClientInputPort implements RPCMultiServerClientPort {

	public AnRPCMultiServerPort(SimplexMultiServerClientPort<Object> aMultiServerClientPort) {
		super(aMultiServerClientPort);
	}
	
	
//	}
	
}
