package port.sessionserver.relay.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.relay.ASessionServerRelayerLauncher;

public class DemoerOfAsymmetricRelayerWithSessionServer {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				port.sessionserver.relay.ARelayerSupportingSessionServerLauncher.class,
				ASessionServerRelayerLauncher.class,
				AliceMemberSessionAndRelayClient.class,
				BobServerSessionAndRelayClient.class,
				CathyClientSessionAndRelayClient.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
