package examples.serialization.counter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIRegistryStarter {
	public static void main (String[] args) {
		try {
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
