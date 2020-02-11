package examples.gipc.counter.simple;


import examples.rmi.counter.ADistributedObservableCounter;
import examples.rmi.counter.DistributedCounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;
import port.ATracingConnectionListener;

public class ASimpleGIPCRegistryAndCounterServer  implements SimpleRegistryAndCounterServer{	
	protected static DistributedCounter counter;
	protected static GIPCRegistry gipcRegistry;
	/**
	 * Register remote object and add connect listener
	 */
	protected static void init() {
		gipcRegistry = GIPCLocateRegistry.createRegistry(SERVER_PORT);
		counter = new ADistributedObservableCounter();			
		gipcRegistry.rebind(COUNTER_NAME, counter);	
		gipcRegistry.getInputPort().addConnectionListener(new ATracingConnectionListener(gipcRegistry.getInputPort()));
	}
	public static void main (String[] args) {		
//		BufferTraceUtility.setTracing();
//		RPCTraceUtility.setTracing();
		init();
	}
}
