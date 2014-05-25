package port.sessionserver.relay.asymmetricexample;

import bus.uigen.models.MainClassLaunchingUtility;

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
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
