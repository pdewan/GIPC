package sessionport.rpc.duplex;

import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.rpc.duplex.ADuplexRPCServerInputPort;

public class ADuplexRPCSessionPort extends ADuplexRPCServerInputPort implements DuplexRPCSessionPort{

	public ADuplexRPCSessionPort(
			DuplexServerInputPort<Object> anObjectServerInputPort) {
		super(anObjectServerInputPort);
	}

	
}
