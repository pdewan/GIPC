package rmi.equals.examples;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ComparableCounterServer extends ComparableCounterLauncher{
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			ComparableCounter counter1 = new AComparableCounter();
			ComparableCounter counter2 = new AComparableCounter();			
			UnicastRemoteObject.exportObject(counter1, 0);
			UnicastRemoteObject.exportObject(counter2, 0);
			rmiRegistry.rebind(COUNTER1, counter1);
			rmiRegistry.rebind(COUNTER2, counter2);	
			ComparableCounter proxy1 = (ComparableCounter) rmiRegistry.lookup(COUNTER1);
			System.out.println(counter1.equals(proxy1));
			System.out.println(counter1.hashCode() == proxy1.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
