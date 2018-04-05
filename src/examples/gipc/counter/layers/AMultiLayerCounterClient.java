package examples.gipc.counter.layers;

import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.nio.ByteBuffer;

import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.AGIPCCounterClient;
import examples.gipc.counter.simple.ASimpleGIPCCounterClient;
/**
 * A class that shows that same port (message queue) can be used to send
 * bytebuffers, objects or remote procedure calls.
 *
 */
public class AMultiLayerCounterClient extends ASimpleGIPCCounterClient {
	protected static DuplexRPCClientInputPort duplexRPCClientInputPort;
	/**
	 * Get the port used by gipc rpc system
	 */
	protected static void setPort() {
		duplexRPCClientInputPort = gipcRegistry.getRPCClientPort();
	}
	/**
	 * Send object on rpc port
	 */
	protected static void sendObjects() {
		duplexRPCClientInputPort.send(2);		
	}
	/**
	 * Send byte buffer on rpc port;
	 */
	protected static void sendByteBuffers() {	
		ByteBuffer aByteBuffer = ByteBuffer.wrap("3".getBytes());
		duplexRPCClientInputPort.send(aByteBuffer);		
	}
	public static void main (String[] args) {	
		RPCTraceUtility.setTracing();
//		BufferTraceUtility.setTracing();
		setPort(); 
		init("Client 1"); // get proxies
		sendByteBuffers();
		sendObjects();
		doOperations();	// send rpc requests on  port	
	}
}
