package examples.gipc.counter;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.AnRMICounterServer;
import examples.rmi.counter.CounterServerLauncher;
import examples.rmi.counter.repository.ARemoteRepository;
import examples.rmi.counter.repository.RemoteRepository;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class AGIPCCounterServer extends CounterServerLauncher implements SimpleRegistryAndCounterServer {	
	public static void main (String[] args) {
		try {
			GIPCRegistry gipcRegistry = GIPCLocateRegistry.createRegistry(SERVER_PORT);
			DistributedRMICounter counter1 = new ADistributedInheritingRMICounter();
			DistributedRMICounter counter2 = new ADistributedInheritingRMICounter();

			gipcRegistry.rebind(COUNTER1, counter1);
			gipcRegistry.rebind(COUNTER2, counter2);	
			DistributedRMICounter fetchedCounter = (DistributedRMICounter) gipcRegistry.lookup(DistributedRMICounter.class, COUNTER1);
			AnRMICounterServer.doOperations(counter1, counter2, fetchedCounter);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
