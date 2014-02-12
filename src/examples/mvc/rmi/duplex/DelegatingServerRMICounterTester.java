package examples.mvc.rmi.duplex;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.rmi.counter.CounterServerLauncher;

public class DelegatingServerRMICounterTester extends CounterServerLauncher {	
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedRMICounter counter1 = new ADistributedDelegatingRMICounter();
			DistributedRMICounter counter2 = new ADistributedDelegatingRMICounter();
			rmiRegistry.rebind(COUNTER1, counter1);
			rmiRegistry.rebind(COUNTER2, counter2);	
			DistributedRMICounter proxy1 = (DistributedRMICounter) rmiRegistry.lookup(COUNTER1);
			System.out.println(counter1.equals(counter2));
			System.out.println(counter1.equals(proxy1));
			System.out.println(counter1.hashCode() == proxy1.hashCode());
			System.out.println(proxy1);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
