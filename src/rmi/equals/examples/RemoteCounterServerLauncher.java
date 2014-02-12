package rmi.equals.examples;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.rmi.duplex.ADistributedDelegatingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;


public class RemoteCounterServerLauncher {
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedRMICounter counter = new ADistributedDelegatingRMICounter();
			//this is what sends a remote rather than serializable version of the object
			UnicastRemoteObject.exportObject(counter, 0);
//			rmiRegistry.rebind(Counter.class.getName(), exportedCounter);
			rmiRegistry.rebind(DistributedRMICounter.class.getName(), counter);
			counter.increment(50);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
