package examples.rmi.arithmetic;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfMixedTypeArithmetic {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				DistributedRMIRegistryStarter.class,
				AMixedTypeArithmeticServerLauncher.class,
				AMixedTypeAirthmeticClientLauncher.class

				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
