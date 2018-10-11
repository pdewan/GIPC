package examples.rmi.arithmetic.stateful;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.Semaphore;

import util.annotations.Tags;
import util.tags.DistributedTags;

@Tags({DistributedTags.RMI, DistributedTags.REGISTRY})
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
