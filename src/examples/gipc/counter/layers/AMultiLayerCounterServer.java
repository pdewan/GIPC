package examples.gipc.counter.layers;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.GIPCRegistry;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import examples.gipc.counter.simple.ASimpleGIPCRegistryAndCounterServer;
/**
 * A class to show how a server can receive bytebuffers, objects and RPC
 * calls on the same port (message queue) 
 *
 */
public class AMultiLayerCounterServer extends ASimpleGIPCRegistryAndCounterServer {
	protected static DuplexRPCServerInputPort duplexRPCServerInputPort;
	/**
	 * Assigns the rpc registry port to a global variable
	 */
	protected static void setPort() {
		duplexRPCServerInputPort = gipcRegistry.getRPCServerPort();
	}
	/**
	 * Adds a single listener for receiving both bytebuffers and objects
	 */
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
