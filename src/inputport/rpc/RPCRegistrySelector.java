package inputport.rpc;

import inputport.rpc.simplex.ASimplexRPCRegistryFactory;

public class RPCRegistrySelector {
	static RPCRegistryFactory rpcRegistryFactory = new ASimplexRPCRegistryFactory();

	public static RPCRegistryFactory getRPCRegistryFactory() {
		return rpcRegistryFactory;
	}

	public static void setRPCRegistryFactory(RPCRegistryFactory rpcRegistryFactory) {
		RPCRegistrySelector.rpcRegistryFactory = rpcRegistryFactory;
	}
	
	public static RPCRegistry createRPCRegistry(RPCInputPort anRPCInputPort) {
		return rpcRegistryFactory.createRPCRegistry(anRPCInputPort);
	}


}
