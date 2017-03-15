package examples.gipc.counter.layers;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.GIPCRegistry;
import examples.gipc.counter.simple.ASimpleGIPCRegistryAndCounterServer;

public class AMultiLayerCounterServer extends ASimpleGIPCRegistryAndCounterServer {
	public static void addListeners() {
		gipcRegistry.getRPCServerPort().addReceiveListener(new AMultiLayeServerReceiveListener(counter));
		
	}
	public static void main (String[] args) {		
//		BufferTraceUtility.setTracing();
//		RPCTraceUtility.setTracing();
		init();
		addListeners();
	}
}
