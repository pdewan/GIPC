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
			Integer intAddition = mixedTypeProcessor.intAdd();
			Double doubleAddition = mixedTypeProcessor.doubleAdd();
			Integer intMultiply = mixedTypeProcessor.intMultiply();
			Double doubleMultiply = mixedTypeProcessor.doubleMultiply();
			// need to print out only initialized values otherwise the grading goes wrong
			if (intAddition != null)
			System.out.println("The int addition:"+ intAddition);
			if (doubleAddition != null)
			System.out.println("The double addition:"+ doubleAddition);
			if (intMultiply != null)
			System.out.println("The int multiplication:"+ intMultiply);
			if (doubleMultiply != null)
			System.out.println("The double multiplication:"+ doubleMultiply);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
