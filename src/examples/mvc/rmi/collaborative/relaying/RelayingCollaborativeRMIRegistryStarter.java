package examples.mvc.rmi.collaborative.relaying;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;


public class RelayingCollaborativeRMIRegistryStarter {
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
