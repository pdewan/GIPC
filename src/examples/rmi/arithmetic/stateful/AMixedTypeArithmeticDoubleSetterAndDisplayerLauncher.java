package examples.rmi.arithmetic.stateful;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import util.annotations.Tags;
import util.tags.DistributedTags;
@Tags({DistributedTags.CLIENT, DistributedTags.CLIENT_2})
public class AMixedTypeArithmeticDoubleSetterAndDisplayerLauncher {
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			RemoteStatefulMixedTypeProcessor mixedTypeProcessor = (RemoteStatefulMixedTypeProcessor) rmiRegistry.lookup(MixedTypeAirthmeticServer.SERVER_NAME);
			Scanner scan = new Scanner(System.in);
			System.out.println("Please input a decimal");
			double num2 = scan.nextDouble();
			mixedTypeProcessor.setDouble(num2);
			System.out.println("The int addition:"+ mixedTypeProcessor.intAdd());
			System.out.println("The double addition:"+ mixedTypeProcessor.doubleAdd());
			System.out.println("The int multiplication:"+ mixedTypeProcessor.intMultiply());
			System.out.println("The double multiplication:"+ mixedTypeProcessor.doubleMultiply());

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
