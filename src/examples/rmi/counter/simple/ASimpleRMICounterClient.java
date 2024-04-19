package examples.rmi.counter.simple;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import examples.rmi.counter.DistributedCounter;

public class ASimpleRMICounterClient implements SimpleCounterClient{
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry(SERVER_HOST_NAME, SERVER_PORT);
//			Registry rmiRegistry = LocateRegistry.getRegistry(SERVER_HOST_NAME, 12345);

			DistributedCounter counter = (DistributedCounter) rmiRegistry.lookup(COUNTER_NAME);			
//			DistributedCounter counter = (DistributedCounter) rmiRegistry.lookup("foo");			

			counter.increment(1);
			System.out.println (counter.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
