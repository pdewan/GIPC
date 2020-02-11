package examples.rmi.counter.simple;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.rmi.counter.ADistributedObservableCounter;
import examples.rmi.counter.DistributedCounter;

public class ASimpleRMIRegistryAndCounterServer  implements SimpleRegistryAndCounterServer{	
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.createRegistry(SERVER_PORT);
			DistributedCounter counter = new ADistributedObservableCounter();			
			UnicastRemoteObject.exportObject(counter, 0);
			rmiRegistry.rebind(COUNTER_NAME, counter);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
