package examples.gipc.counter.layers;

import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import inputport.datacomm.ReceiveListener;
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
	protected static ReceiveListener receiveListener;
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
		receiveListener = new AMultiLayeServerReceiveListener(counter);
		duplexRPCServerInputPort.addReceiveListener(receiveListener);		
	}
	/**
	 * Run the programs with and without the code in main commented out
	 */
	public static void setTracing() {
		ObjectTraceUtility.setTracing();
		RPCTraceUtility.setTracing();
	}
	public static void launch() {
//		setTracing();
		init();
		setPort();
		addListeners();
	}
	
}
