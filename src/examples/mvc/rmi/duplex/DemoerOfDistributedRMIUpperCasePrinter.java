package examples.mvc.rmi.duplex;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDistributedRMIUpperCasePrinter {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				DistributedRMIRegistryStarter.class,
				ADistributedRMIServerMVC_Launcher.class,
				ADistributedRMIClientMVC_Launcher.class				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
