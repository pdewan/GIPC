package inputport.rpc.duplex;

import inputport.rpc.RPCInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.RPCRegistryFactory;


public class ADuplexRPCRegistryFactory implements RPCRegistryFactory {
	@Override
	public RPCRegistry createRPCRegistry(RPCInputPort anRPCInputPort) {
		return new ADuplexRPCRegistry(anRPCInputPort);
	}

}
