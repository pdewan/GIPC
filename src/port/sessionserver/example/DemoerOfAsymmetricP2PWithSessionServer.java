package port.sessionserver.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
/*
 * Alice can send to others but Bob cannot.
 * Killing session server keeps alice output going
 */
public class DemoerOfAsymmetricP2PWithSessionServer {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				port.sessionserver.ASessionServerLauncher.class,
				AliceMemberSessionClient.class,
				BobServerSessionClient.class,
				CathyClientSessionClient.class

				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
