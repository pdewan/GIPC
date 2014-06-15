package examples.mvc.rmi.muser;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfMultiUserRMI_MVC {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				MultiUserRMIRegistryStarter.class,
				AMultiUserRMIServerMVC_Launcher.class,
				AliceMultiUserRMIUpperCaseLauncher.class,
				BobMultiUserRMIUpperCaseLauncher.class,
				CathyMultiUserRMIUpperCaseLauncher.class

				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
