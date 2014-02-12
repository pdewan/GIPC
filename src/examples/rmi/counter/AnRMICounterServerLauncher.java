package examples.rmi.counter;


import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;

public class AnRMICounterServerLauncher extends CounterServerLauncher {	
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedRMICounter counter1 = new ADistributedInheritingRMICounter();
			DistributedRMICounter counter2 = new ADistributedInheritingRMICounter();
			RemoteRepository counterRepository = new ARemoteRepository();
			UnicastRemoteObject.exportObject(counter1, 0);
			UnicastRemoteObject.exportObject(counter2, 0);
//			UnicastRemoteObject.exportObject(counterRepository, 0);
			rmiRegistry.rebind(COUNTER1, counter1);
			rmiRegistry.rebind(COUNTER2, counter2);	
//			rmiRegistry.rebind(COUNTER_REPOSITORY, counterRepository);			
			DistributedRMICounter proxy1 = (DistributedRMICounter) rmiRegistry.lookup(COUNTER1);
			System.out.println(counter1.equals(counter2));
			System.out.println(counter1.equals(proxy1));
			System.out.println(counter1.hashCode() == proxy1.hashCode());
			System.out.println(proxy1);
//			Remote obj = rmiRegistry.lookup("foo");
//			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
