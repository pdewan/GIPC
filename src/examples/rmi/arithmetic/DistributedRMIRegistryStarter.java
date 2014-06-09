package examples.rmi.arithmetic;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import util.annotations.Tags;

@Tags({"RMIRegistry", "Registry"})
public class DistributedRMIRegistryStarter {
	public static void main (String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			(new Semaphore(0)).acquire(); // wait for ever to avoid termination
//			Scanner scanner = new Scanner(System.in);
//			scanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
