package examples.rmi.counter.repository;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AnRMIRepositoryServerLauncher extends RemoteRepositoryLauncher {	
	public static void main (String[] args) {
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			RemoteRepository repository = new ARemoteRepository();			
			UnicastRemoteObject.exportObject(repository, 0);
			rmiRegistry.rebind(COUNTER_REPOSITORY, repository);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
