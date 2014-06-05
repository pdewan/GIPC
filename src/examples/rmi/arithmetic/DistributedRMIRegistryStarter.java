package examples.rmi.arithmetic;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import util.annotations.Tags;

@Tags({"RMIRegistry"})
public class DistributedRMIRegistryStarter {
	public static void main (String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
