package examples.mvc.local.simplex;

import examples.mvc.rmi.duplex.DistributedRMIRegistryStarter;
import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfSimplexMVC {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
		
		Class[] classes = {
				ASimplexFrostyLauncher.class				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
