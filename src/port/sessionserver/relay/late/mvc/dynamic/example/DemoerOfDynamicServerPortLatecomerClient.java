package port.sessionserver.relay.late.mvc.dynamic.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDynamicServerPortLatecomerClient {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ALatecomerSessionServerLauncher.class,
				ASessionMemberMVCServerLauncher.class,
				AliceDynamicServerLatecomerClientLauncher.class,
				BobDynamicServerLatecomerClientLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
