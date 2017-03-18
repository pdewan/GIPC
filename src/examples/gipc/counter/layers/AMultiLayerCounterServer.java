package examples.gipc.counter.layers;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.GIPCRegistry;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import examples.gipc.counter.simple.ASimpleGIPCRegistryAndCounterServer;

public class AMultiLayerCounterServer extends ASimpleGIPCRegistryAndCounterServer {
	protected static DuplexRPCServerInputPort duplexRPCServerInputPort;
	protected static void setPort() {
		duplexRPCServerInputPort = gipcRegistry.getRPCServerPort();
	}
	public static void addListeners() {
		duplexRPCServerInputPort.addReceiveListener(new AMultiLayeServerReceiveListener(counter));		
	}
	public static void main (String[] args) {		
//		BufferTraceUtility.setTracing();
//		RPCTraceUtility.setTracing();
		init();
		setPort();
		addListeners();
	}
}
