package port.sessionserver.relay.mvc.example;

import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfThreeDepPortClient {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ARelayerSupportingSessionServerLauncher.class,
				ASessionServerRelayerLauncher.class,
				ASessionMemberMVCServerLauncher.class,
				AliceThreeDepPortClientLauncher.class,
				BobThreeDepPortClientLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}

}
