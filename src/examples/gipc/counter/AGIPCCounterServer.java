package examples.gipc.counter;


import examples.rmi.counter.ADistributedObservableCounter;
import examples.rmi.counter.CounterServer;
import examples.rmi.counter.DistributedCounter;
import examples.rmi.counter.RMICounterServerLauncher;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

public class AGIPCCounterServer implements SimpleRegistryAndCounterServer {	
	public static void main (String[] args) {
		try {
			GIPCRegistry gipcRegistry = GIPCLocateRegistry.createRegistry(SERVER_PORT);
			DistributedCounter counter1 = new ADistributedObservableCounter();
			DistributedCounter counter2 = new ADistributedObservableCounter();

			gipcRegistry.rebind(CounterServer.COUNTER1, counter1);
			gipcRegistry.rebind(CounterServer.COUNTER2, counter2);	
			DistributedCounter fetchedCounter = (DistributedCounter) gipcRegistry.lookup(DistributedCounter.class, CounterServer.COUNTER1);
			RMICounterServerLauncher.doOperations(counter1, counter2, fetchedCounter);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
