package rmi.equals.examples;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ComparableCounterClient extends ComparableCounterLauncher{
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			ComparableCounter counter11 = (ComparableCounter) rmiRegistry.lookup(COUNTER1);
			ComparableCounter counter12 = (ComparableCounter) rmiRegistry.lookup(COUNTER1);
			ComparableCounter counter2 = (ComparableCounter) rmiRegistry.lookup(COUNTER2);		
			ComparableCounter greaterCounter = counter11.greater(counter11);
			System.out.println(greaterCounter == counter11);
			System.out.println(greaterCounter.equals(counter11));
			System.out.println(counter12 == counter11);
			System.out.println(counter12.equals(counter11));
			System.out.println(counter11.hashCode() == counter12.hashCode());
			System.out.println(greaterCounter.hashCode() == counter11.hashCode());
			System.out.println(counter11.equals(counter2));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
