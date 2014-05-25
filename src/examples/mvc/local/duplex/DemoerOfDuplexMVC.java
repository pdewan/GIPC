package examples.mvc.local.duplex;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDuplexMVC {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ADuplexFrostyLauncher.class
//				DistributedRMIRegistryStarter.class,
//				ADistributedRMIUpperCaseServerLauncher.class,
//				ADistributedRMIUpperCaseClientLauncher.class				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
