package examples.serialization.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import examples.rmi.counter.ADistributedCounter;
import examples.rmi.counter.ADistributedObservableCounter;
import examples.rmi.counter.CounterServer;
import examples.rmi.counter.DistributedCounter;

public class SerializedCounterServerLauncher  {	
		
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
//			DistributedCounter aCounter = new ASerializableCounter();
//			DistributedCounter aCounter = new ADistributedCounter();
			DistributedCounter aCounter = new ASerializableDelegatingCounter(new ADistributedCounter());
//			DistributedCounter aCounter = new ASerializableDelegatingCounter(new ASerializableCounter());

			rmiRegistry.rebind(CounterServer.COUNTER1, aCounter);
			

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
