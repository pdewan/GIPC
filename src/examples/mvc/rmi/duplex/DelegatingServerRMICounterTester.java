package examples.mvc.rmi.duplex;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import examples.rmi.counter.CounterServer;
import examples.rmi.counter.DistributedCounter;

public class DelegatingServerRMICounterTester {	
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedCounter counter1 = new ADistributedDelegatingRMICounter();
			DistributedCounter counter2 = new ADistributedDelegatingRMICounter();
			rmiRegistry.rebind(CounterServer.COUNTER1, counter1);
			rmiRegistry.rebind(CounterServer.COUNTER2, counter2);	
			DistributedCounter proxy1 = (DistributedCounter) rmiRegistry.lookup(CounterServer.COUNTER1);
			System.out.println(counter1.equals(counter2));
			System.out.println(counter1.equals(proxy1));
			System.out.println(counter1.hashCode() == proxy1.hashCode());
			System.out.println(proxy1);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
