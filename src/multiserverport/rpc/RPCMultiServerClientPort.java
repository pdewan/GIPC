package multiserverport.rpc;

import inputport.rpc.simplex.SimplexRPC;
import multiserverport.datacomm.simplex.SimplexMultiServerClientPort;


public interface RPCMultiServerClientPort extends SimplexRPC, SimplexMultiServerClientPort<Object>{
//	TypedClientInputPort getTypedClientInputPort();

}
