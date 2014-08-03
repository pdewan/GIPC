package port.sessionserver.relay.late.mvc.example;

import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfTwoDepPortLatecomerClient {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ALatecomerSessionServerLauncher.class,
				ASessionMemberMVCServerLauncher.class,
				AliceTwoDepPortLatecomerClientLauncher.class,
				BobTwoDepPortLatecomerClientLauncher.class,
				CathyTwoDepPortLatecomerClientLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
