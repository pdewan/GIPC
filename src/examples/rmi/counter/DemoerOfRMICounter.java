package examples.rmi.counter;

import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfRMICounter {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				RMIRegistryStarter.class,
				RMICounterServerLauncher.class,
				RMICounterClientLauncher.class

				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
