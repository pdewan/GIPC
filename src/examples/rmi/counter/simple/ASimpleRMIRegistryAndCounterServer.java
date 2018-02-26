package examples.rmi.counter.simple;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;

public class ASimpleRMIRegistryAndCounterServer  implements SimpleRegistryAndCounterServer{	
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.createRegistry(SERVER_PORT);
			DistributedRMICounter counter = new ADistributedInheritingRMICounter();			
			UnicastRemoteObject.exportObject(counter, 0);
			rmiRegistry.rebind(COUNTER_NAME, counter);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
