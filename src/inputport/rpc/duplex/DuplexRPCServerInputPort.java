package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.rpc.simplex.SimplexRPCServerInputPort;


public interface DuplexRPCServerInputPort extends DuplexServerInputPort<Object>, SimplexRPCServerInputPort, DuplexRPCInputPort{

}
