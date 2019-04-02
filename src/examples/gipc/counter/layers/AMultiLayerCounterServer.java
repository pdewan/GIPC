package examples.gipc.counter.layers;

import examples.gipc.counter.simple.ASimpleGIPCRegistryAndCounterServer;
import inputport.datacomm.ReceiveListener;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
/**
 * A class to show how a server can receive bytebuffers, objects and RPC
 * calls on the same port (message queue).
 * 
 * The RPC call invokes the increment method in the remotely registered object.
 * 
 * The bytebuffer and object messages are used  by their listener to locally
 * call the increment method
 *
 */
public class AMultiLayerCounterServer extends ASimpleGIPCRegistryAndCounterServer {
	protected static DuplexRPCServerInputPort duplexRPCServerInputPort;
	protected static ReceiveListener receiveListener;
	/**
	 * Assigns the rpc port associated with a registry to a global variable
	 */
	protected static void setPort() {
		duplexRPCServerInputPort = gipcRegistry.getRPCServerPort();
	}
	/**
	 * Adds a single listener for receiving both bytebuffers and objects
	 * explictly received message should also be delivered to the listener
	 */
	
	public static void addListeners() {
		receiveListener = new AMultiLayeServerReceiveListener(counter);
		duplexRPCServerInputPort.addReceiveListener(receiveListener);		
	}
	
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
