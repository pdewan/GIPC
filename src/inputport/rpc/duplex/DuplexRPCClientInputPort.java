package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.rpc.simplex.SimplexRPCClientInputPort;

public interface DuplexRPCClientInputPort extends SimplexRPCClientInputPort, DuplexRPCInputPort, DuplexClientInputPort<Object>{

}
