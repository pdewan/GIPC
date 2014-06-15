package examples.mvc.rmi.collaborative.relaying;
import bus.uigen.models.MainClassLaunchingUtility;
public class DemoerOfRelayingCollaborativeRMI_MVC {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				RelayingCollaborativeRMIRegistryStarter.class,
				ARelayingCollaborativeRMIServerMVC_Launcher.class,
				AliceRelayingCollaborativeRMIUpperCaseLauncher.class,
				BobRelayingCollaborativeRMIUpperCaseLauncher.class,
				CathyRelayingCollaborativeRMIUpperCaseLauncher.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
}
