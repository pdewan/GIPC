package multiserverport.rpc.duplex;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;

public interface DuplexRPCMultiServerClientPort extends DuplexMultiServerClientPort<Object>, DuplexRPCClientInputPort{

}
