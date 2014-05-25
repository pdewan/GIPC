package port.sessionserver.asymmetricexample;

import bus.uigen.models.MainClassLaunchingUtility;

public class AsymmetricSessionServerDemoer {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASessionServerLauncher.class,
				AnAliceMemberSessionClient.class,
				ABobServerSessionClient.class,
				ACathyClientSessionClient.class

				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
