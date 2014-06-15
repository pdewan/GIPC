package port.sessionserverAndRelay.mvc.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfThreeIndepPortClient {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ASessionServerLauncher.class,
				ARelayerLauncher.class,
				AGroupRPCServerCollaborativeMVCLauncher.class,
				AliceThreeIndepPortClientLauncher.class,
				BobThreeIndepPortClientLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
