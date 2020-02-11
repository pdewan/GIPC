package examples.rmi.counter.repository;


import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import examples.rmi.counter.ADistributedObservableCounter;
import examples.rmi.counter.DistributedCounter;

public class AnRMIRepositoryClientLauncher extends RemoteRepositoryLauncher{
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			RemoteRepository counterRepository = (RemoteRepository) rmiRegistry.lookup(COUNTER_REPOSITORY);
			DistributedCounter exportedCounter = new ADistributedObservableCounter();
			UnicastRemoteObject.exportObject(exportedCounter, 0);
			counterRepository.deposit(exportedCounter);
			exportedCounter.increment(1);				
			List<Remote> objects = counterRepository.getObjects();
			for (Remote counter:objects) {
				System.out.println(((DistributedCounter) counter).getValue());
				System.out.println(counter == exportedCounter);
				System.out.println(counter.equals(exportedCounter));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
