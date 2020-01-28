package rmi.equals.examples;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import examples.rmi.counter.DistributedCounter;


public class RemoteCounterClientLauncher {
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedCounter counter = (DistributedCounter) rmiRegistry.lookup(DistributedCounter.class.getName());
			System.out.println(counter.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
