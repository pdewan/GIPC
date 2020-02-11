package examples.rmi.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICounterClientLauncher   {
	public static void doOperations(DistributedCounter counter11 , DistributedCounter counter12, DistributedCounter counter2) {
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

			DistributedCounter counter11 = (DistributedCounter) rmiRegistry.lookup(CounterServer.COUNTER1);
			DistributedCounter counter12 = (DistributedCounter) rmiRegistry.lookup(CounterServer.COUNTER1);
			DistributedCounter counter2 = (DistributedCounter) rmiRegistry.lookup(CounterServer.COUNTER2);
			counter11.increment(-25);
		    System.out.println ("Client:" + counter11.getValue());
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
