package examples.gipc.counter.layers;

import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.nio.ByteBuffer;

import util.misc.ThreadSupport;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.AGIPCCounterClient;
import examples.gipc.counter.simple.ASimpleGIPCCounterClient;
/**
 * A class that shows that same port (message queue) can be used to send
 * bytebuffers, objects or remote procedure calls.
 *
 */
public class AMultiLayerCounterClient extends ASimpleGIPCCounterClient {
	public static final int WAIT_TIME_BETWEEEN_SENDS = 3000; 
	protected static DuplexRPCClientInputPort duplexRPCClientInputPort;
	/*
	 * Constants indicating the values sent as objects and buffers to increment
	 * the counter 
	 */
	public static final int OBJECT_INCREMENT = 2;
	public static final String  BUFFER_INCREMENT = "3";

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
		System.out.println("Sending counter increment message:" + OBJECT_INCREMENT);

		duplexRPCClientInputPort.send(OBJECT_INCREMENT);		
	}
	/**
	 * Send byte buffer on rpc port;
	 */
	protected static void sendByteBuffers() {	
		ByteBuffer aByteBuffer = ByteBuffer.wrap("3".getBytes());
		System.out.println("Sending counter increment message:" + aByteBuffer);
		duplexRPCClientInputPort.send(aByteBuffer);		
	}
	public static void launchClient(String aClientName) {
//		AMultiLayerCounterServer.setTracing(); 
		init(aClientName); // get proxies, init registry
		setPort(); 
		ThreadSupport.sleep(WAIT_TIME_BETWEEEN_SENDS);
		sendByteBuffers();
		ThreadSupport.sleep(WAIT_TIME_BETWEEEN_SENDS);
		sendObjects();
		ThreadSupport.sleep(WAIT_TIME_BETWEEEN_SENDS);
		doOperations();	// send rpc requests on  port
	}
	
}
