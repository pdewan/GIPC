package examples.gipc.counter.customport;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import port.trace.rpc.RPCTraceUtility;
import examples.gipc.counter.layers.AMultiLayerCounterClient;
import examples.gipc.counter.layers.AMultiLayerCounterServer;

public class ACustomCounterServer extends AMultiLayerCounterServer{
	
	public static void main (String[] args) {		
		ACustomCounterClient.setFactories();
//		BufferTraceUtility.setTracing();
//		RPCTraceUtility.setTracing();
		init();
		addListeners();
	}
	

}
