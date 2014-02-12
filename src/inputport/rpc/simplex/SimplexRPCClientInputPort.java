package inputport.rpc.simplex;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.rpc.RPCInputPort;


public interface SimplexRPCClientInputPort extends RPCInputPort, SimplexRPC, SimplexClientInputPort<Object>{

}
