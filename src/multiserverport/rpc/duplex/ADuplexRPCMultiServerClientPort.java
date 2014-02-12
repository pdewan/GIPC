package multiserverport.rpc.duplex;

import inputport.rpc.duplex.ADuplexRPCClientInputPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;



public class ADuplexRPCMultiServerClientPort extends ADuplexRPCClientInputPort implements DuplexRPCMultiServerClientPort{
	DuplexMultiServerClientPort<Object> duplexMultiServerClientPort;

	public ADuplexRPCMultiServerClientPort(DuplexMultiServerClientPort<Object> anObjectClientInputPort) {
		super(anObjectClientInputPort);
		duplexMultiServerClientPort = anObjectClientInputPort;
		
	}
	

}
