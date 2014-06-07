package examples.rmi.arithmetic;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import util.annotations.Tags;
@Tags({"RMIClient"})
public class AMixedTypeAirthmeticClientLauncher {
	public static void main (String[] args) {	
		try {
			Registry rmiRegistry = LocateRegistry.getRegistry();
			RemoteMixedTypeProcessor mixedTypeProcessor = (RemoteMixedTypeProcessor) rmiRegistry.lookup(MixedTypeAirthmeticServer.SERVER_NAME);
			Scanner scan = new Scanner(System.in);
			System.out.println("Please input an integer");
			int num1 = scan.nextInt();
			System.out.println("Please input a decimal");
			double num2 = scan.nextDouble();
			System.out.println("The int addition:"+ mixedTypeProcessor.intAdd(num1, num2));
			System.out.println("The double addition:"+ mixedTypeProcessor.doubleAdd(num1, num2));
			System.out.println("The int multiplication:"+ mixedTypeProcessor.intMultiply(num1, num2));
			System.out.println("The double multiplication:"+ mixedTypeProcessor.doubleMultiply(num1, num2));

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
