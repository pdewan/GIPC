package inputport.rpc.simplex;

import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.rpc.RPCInputPort;
import inputport.rpc.RPCRegistry;

public interface SimplexRPCServerInputPort extends RPCInputPort, SimplexServerInputPort<Object>, RPCRegistry {

}
