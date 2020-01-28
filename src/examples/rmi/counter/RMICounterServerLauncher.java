package examples.rmi.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMICounterServerLauncher  {	
	public static void doOperations(
			DistributedCounter counter1,
			DistributedCounter counter2,
			DistributedCounter fetchedCounter) {
		System.out.println(counter1.equals(counter2));
		System.out.println(counter1.equals(fetchedCounter));
		System.out.println(counter1.hashCode() == fetchedCounter.hashCode());
		System.out.println(fetchedCounter);
		System.out.println(counter1 == fetchedCounter);
	}
			
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedCounter counter1 = new ADistributedCounter();
			DistributedCounter counter2 = new ADistributedCounter();
			UnicastRemoteObject.exportObject(counter1, 0);
			UnicastRemoteObject.exportObject(counter2, 0);
			rmiRegistry.rebind(CounterServer.COUNTER1, counter1);
			rmiRegistry.rebind(CounterServer.COUNTER2, counter2);	
			DistributedCounter fetchedCounter = (DistributedCounter) rmiRegistry.lookup(CounterServer.COUNTER1);
			doOperations(counter1, counter2, fetchedCounter);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
