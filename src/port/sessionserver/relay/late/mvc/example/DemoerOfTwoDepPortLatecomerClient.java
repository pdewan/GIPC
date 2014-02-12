package port.sessionserver.relay.late.mvc.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfTwoDepPortLatecomerClient {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ALatecomerSessionServerLauncher.class,
				ASessionMemberMVCServerLauncher.class,
				AliceTwoDepPortLatecomerClientLauncher.class,
				BobTwoDepPortLatecomerClientLauncher.class
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
