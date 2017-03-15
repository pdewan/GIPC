package examples.gipc.counter.customport;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import port.trace.rpc.RPCTraceUtility;
import examples.gipc.counter.layers.AMultiLayerCounterClient;

public class ACustomCounterClient extends AMultiLayerCounterClient{
	public static void setFactories() {
		DuplexObjectInputPortSelector.setDuplexInputPortFactory(new ACustomDuplexObjectInputPortFactory());
	}
	public static void main (String[] args) {	
		RPCTraceUtility.setTracing();
		setFactories();
//		BufferTraceUtility.setTracing();
		init("Client 1");
		sendByteBuffers();
		sendObjects();
		doOperations();		
	}
	

}
