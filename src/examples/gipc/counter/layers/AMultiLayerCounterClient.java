package examples.gipc.counter.layers;

import java.nio.ByteBuffer;

import port.trace.rpc.RPCTraceUtility;
import examples.gipc.counter.AGIPCCounterClient;
import examples.gipc.counter.simple.ASimpleGIPCCounterClient;

public class AMultiLayerCounterClient extends ASimpleGIPCCounterClient {
	protected static void sendObjects() {
		gipcRegistry.getRPCClientPort().send(2);		
	}
	protected static void sendByteBuffers() {	
		ByteBuffer aByteBuffer = ByteBuffer.wrap("3".getBytes());
		gipcRegistry.getRPCClientPort().send(aByteBuffer);		
	}
	public static void main (String[] args) {	
		RPCTraceUtility.setTracing();
//		BufferTraceUtility.setTracing();
		init("Client 1");
		sendByteBuffers();
		sendObjects();
		doOperations();		
	}
}
