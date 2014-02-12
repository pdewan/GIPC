package examples.mvc.rmi.collaborative;

import examples.mvc.rmi.duplex.DistributedRMIRegistryStarter;
import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfCollaborativeRMIMVC {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				CollaborativeRMIRegistryStarter.class,
				ACollaborativeRMIServerMVCLauncher.class,
				AliceCollaborativeRMIUpperCaseLauncher.class,
				BobCollaborativeRMIUpperCaseLauncher.class,
				CathyCollaborativeRMIUpperCaseLauncher.class

				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
