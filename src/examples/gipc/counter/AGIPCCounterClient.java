package examples.gipc.counter;


import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.AnRMICounterClient;
import examples.rmi.counter.CounterServerLauncher;
import examples.rmi.counter.simple.SimpleCounterClient;
import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

public class AGIPCCounterClient extends CounterServerLauncher implements SimpleCounterClient{
	public static void test(GIPCRegistry gipcRegistry, Class aLookedupClass) {
		DistributedRMICounter counter11 = (DistributedRMICounter) gipcRegistry.lookup(aLookedupClass, COUNTER1);
		DistributedRMICounter counter12 = (DistributedRMICounter) gipcRegistry.lookup(aLookedupClass, COUNTER1);
		DistributedRMICounter counter2 = (DistributedRMICounter) gipcRegistry.lookup(aLookedupClass, COUNTER2);		
		AnRMICounterClient.doOperations(counter11, counter12, counter2);
	}
	public static void main (String[] args) {	
		try {
			GIPCRegistry gipcRegistry = GIPCLocateRegistry.getRegistry(SERVER_HOST_NAME, SERVER_PORT, "client");
//			test(gipcRegistry, DistributedRMICounter.class);
			ACachingAbstractRPCProxyInvocationHandler.setInvokeObjectMethodsRemotely(true);
//			test(gipcRegistry, DistributedRMICounter.class);
			test(gipcRegistry, ADistributedInheritingRMICounter.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
