package examples.rmi.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.repository.ARemoteRepository;
import examples.rmi.counter.repository.RemoteRepository;

public class AnRMICounterServer extends CounterServerLauncher {	
	public static void doOperations(
			DistributedRMICounter counter1,
			DistributedRMICounter counter2,
			DistributedRMICounter fetchedCounter) {
		System.out.println(counter1.equals(counter2));
		System.out.println(counter1.equals(fetchedCounter));
		System.out.println(counter1.hashCode() == fetchedCounter.hashCode());
		System.out.println(fetchedCounter);
		System.out.println(counter1 == fetchedCounter);
	}
			
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedRMICounter counter1 = new ADistributedInheritingRMICounter();
			DistributedRMICounter counter2 = new ADistributedInheritingRMICounter();
			UnicastRemoteObject.exportObject(counter1, 0);
			UnicastRemoteObject.exportObject(counter2, 0);
			rmiRegistry.rebind(COUNTER1, counter1);
			rmiRegistry.rebind(COUNTER2, counter2);	
			DistributedRMICounter fetchedCounter = (DistributedRMICounter) rmiRegistry.lookup(COUNTER1);
			doOperations(counter1, counter2, fetchedCounter);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
