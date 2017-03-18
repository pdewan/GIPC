package examples.gipc.counter.layers;

import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.nio.ByteBuffer;

import port.trace.rpc.RPCTraceUtility;
import examples.gipc.counter.AGIPCCounterClient;
import examples.gipc.counter.simple.ASimpleGIPCCounterClient;

public class AMultiLayerCounterClient extends ASimpleGIPCCounterClient {
	protected static DuplexRPCClientInputPort duplexRPCClientInputPort;
	protected static void setPort() {
		duplexRPCClientInputPort = gipcRegistry.getRPCClientPort();
	}
	protected static void sendObjects() {
		duplexRPCClientInputPort.send(2);		
	}
	protected static void sendByteBuffers() {	
		ByteBuffer aByteBuffer = ByteBuffer.wrap("3".getBytes());
		duplexRPCClientInputPort.send(aByteBuffer);		
	}
	public static void main (String[] args) {	
		RPCTraceUtility.setTracing();
//		BufferTraceUtility.setTracing();
		setPort();
		init("Client 1");
		sendByteBuffers();
		sendObjects();
		doOperations();		
	}
}
