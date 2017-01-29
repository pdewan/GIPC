package examples.rmi.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;

public class AnRMICounterClientLauncher extends CounterServerLauncher{
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
//			DistributedRMICounter transmittedCounter =  new ADistributedInheritingRMICounter();		
//			CounterRepository counterRepository = (CounterRepository) rmiRegistry.lookup(COUNTER_REPOSITORY);
			DistributedRMICounter counter11 = (DistributedRMICounter) rmiRegistry.lookup(COUNTER1);
			DistributedRMICounter counter12 = (DistributedRMICounter) rmiRegistry.lookup(COUNTER1);
			DistributedRMICounter counter2 = (DistributedRMICounter) rmiRegistry.lookup(COUNTER2);
//			DistributedRMICounter exportedCounter = new ADistributedInheritingRMICounter();
//			UnicastRemoteObject.exportObject(exportedCounter, 0);
//			rmiRegistry.bind("foo", exportedCounter);						
//			counterRepository.deposit(transmittedCounter);
//			transmittedCounter.increment(1);			
			System.out.println(counter12 == counter11);
			System.out.println(counter12.equals(counter11));
			System.out.println(counter11.hashCode() == counter12.hashCode());
			System.out.println(counter11.equals(counter2));
			System.out.println(counter11.hashCode() == counter2.hashCode());
			System.out.println(counter12);
			counter11.increment(1);
			System.out.println (counter11.getValue());
//			List<DistributedRMICounter> counters = counterRepository.getCounters();
//			for (DistributedRMICounter counter:counters) {
//				System.out.println(counter.getValue());
//				System.out.println(counter == transmittedCounter);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
//	public static void remoteEqualsIssue () {	
//		Object counter1 = null;
//		Object counter2 = null;
//		try {
//			Registry rmiRegistry = LocateRegistry.getRegistry();
//			counter1 = rmiRegistry.lookup(COUNTER1);
//			counter2 = rmiRegistry.lookup(COUNTER2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(counter1.equals(counter2));
//	}
}
