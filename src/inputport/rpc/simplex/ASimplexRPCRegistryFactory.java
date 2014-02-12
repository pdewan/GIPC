package inputport.rpc.simplex;

import inputport.rpc.RPCInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.RPCRegistryFactory;


public class ASimplexRPCRegistryFactory implements RPCRegistryFactory {
	@Override
	public RPCRegistry createRPCRegistry(RPCInputPort anRPCInputPort) {
		return new ASimplexRPCRegistry();
	}

}
