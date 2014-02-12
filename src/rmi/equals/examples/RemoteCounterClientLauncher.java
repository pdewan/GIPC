package rmi.equals.examples;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import examples.mvc.rmi.duplex.DistributedRMICounter;


public class RemoteCounterClientLauncher {
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedRMICounter counter = (DistributedRMICounter) rmiRegistry.lookup(DistributedRMICounter.class.getName());
			System.out.println(counter.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
