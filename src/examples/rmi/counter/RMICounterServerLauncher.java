package examples.rmi.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.serialization.counter.ASerializableCounter;

public class RMICounterServerLauncher  {	
	public static void doOperations(
			DistributedCounter counter1,
			DistributedCounter counter2,
			DistributedCounter fetchedCounter) {
		System.out.println(counter1.equals(counter2));
		System.out.println(counter1.equals(fetchedCounter));
//		System.out.println(counter1.hashCode() == fetchedCounter.hashCode());
		System.out.println(fetchedCounter);
//		System.out.println(counter1.hashCode() == counter2.hashCode());

		System.out.println(counter1 == fetchedCounter);
	}
			
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedCounter counter1 = new ADistributedObservableCounter();
//			DistributedCounter counter1 = new ASerializableCounter();
			DistributedCounter counter2 = new ADistributedObservableCounter();
			UnicastRemoteObject.exportObject(counter1, 0);
			UnicastRemoteObject.exportObject(counter2, 0);
			rmiRegistry.rebind(CounterServer.COUNTER1, counter1);
			rmiRegistry.rebind(CounterServer.COUNTER2, counter2);
//			counter1.increment(50);
		    System.out.println ("Server:" + counter1.getValue());

			DistributedCounter fetchedCounter = (DistributedCounter) rmiRegistry.lookup(CounterServer.COUNTER1);
			doOperations(counter1, counter2, fetchedCounter);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
