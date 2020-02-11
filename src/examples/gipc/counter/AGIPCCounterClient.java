package examples.gipc.counter;


import examples.rmi.counter.ADistributedObservableCounter;
import examples.rmi.counter.CounterServer;
import examples.rmi.counter.DistributedCounter;
import examples.rmi.counter.RMICounterClientLauncher;
import examples.rmi.counter.simple.SimpleCounterClient;
import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

public class AGIPCCounterClient implements SimpleCounterClient{
	public static void test(GIPCRegistry gipcRegistry, Class aLookedupClass) {
		DistributedCounter counter11 = (DistributedCounter) gipcRegistry.lookup(aLookedupClass,  CounterServer.COUNTER1);
		DistributedCounter counter12 = (DistributedCounter) gipcRegistry.lookup(aLookedupClass, CounterServer.COUNTER1);
		DistributedCounter counter2 = (DistributedCounter) gipcRegistry.lookup(aLookedupClass, CounterServer.COUNTER2);		
		RMICounterClientLauncher.doOperations(counter11, counter12, counter2);
	}
	public static void main (String[] args) {	
		try {
			GIPCRegistry gipcRegistry = GIPCLocateRegistry.getRegistry(SERVER_HOST_NAME, SERVER_PORT, "client");
//			test(gipcRegistry, DistributedRMICounter.class);
			ACachingAbstractRPCProxyInvocationHandler.setInvokeObjectMethodsRemotely(true);
//			test(gipcRegistry, DistributedRMICounter.class);
			test(gipcRegistry, ADistributedObservableCounter.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
