package examples.rmi.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import examples.mvc.rmi.duplex.DistributedRMICounter;

public class AnRMICounterClient extends CounterServerLauncher{
	public static void doOperations(DistributedRMICounter counter11 , DistributedRMICounter counter12, DistributedRMICounter counter2) {
		try {
		System.out.println(counter12 == counter11);
		System.out.println(counter12.equals(counter11));
		System.out.println(counter11.hashCode() == counter12.hashCode());
		System.out.println(counter11.equals(counter2));
		System.out.println(counter11.hashCode() == counter2.hashCode());
		System.out.println(counter12);
		counter11.increment(1);
		counter2.increment(1);
		System.out.println (counter11.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();

			DistributedRMICounter counter11 = (DistributedRMICounter) rmiRegistry.lookup(COUNTER1);
			DistributedRMICounter counter12 = (DistributedRMICounter) rmiRegistry.lookup(COUNTER1);
			DistributedRMICounter counter2 = (DistributedRMICounter) rmiRegistry.lookup(COUNTER2);
			doOperations(counter11, counter12, counter2);
			
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
