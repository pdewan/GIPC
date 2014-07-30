package examples.rmi.arithmetic.stateful;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import util.annotations.Tags;
import util.tags.DistributedTags;
@Tags({DistributedTags.CLIENT, DistributedTags.CLIENT_1})
public class AMixedTypeArithmeticIntSetterLauncher {
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			RemoteStatefulMixedTypeProcessor mixedTypeProcessor = (RemoteStatefulMixedTypeProcessor) rmiRegistry.lookup(MixedTypeAirthmeticServer.SERVER_NAME);
			Scanner scan = new Scanner(System.in);
			System.out.println("Please input an integer");
			int num1 = scan.nextInt();
			mixedTypeProcessor.setInt(num1);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
