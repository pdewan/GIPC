package examples.gipc.counter.simple;


import examples.rmi.counter.simple.SimpleCounterClient;
import util.trace.port.buffer.BufferTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;

public class ASimpleGIPCCounterClient1 extends ASimpleGIPCCounterClient implements SimpleCounterClient{
	
	
	public static void main (String[] args) {	
		RPCTraceUtility.setTracing();
		BufferTraceUtility.setTracing();
		init("Client 1");
		doOperations();		
	}
}
