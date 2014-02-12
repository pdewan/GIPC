package examples.rmi.counter;

import java.io.IOException;
import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class InteractiveRMIDemoer {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
		String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				DistributedRMIRegistryStarter.class,
				AnRMICounterServerLauncher.class,
				AnRMICounterClientLauncher.class

				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
