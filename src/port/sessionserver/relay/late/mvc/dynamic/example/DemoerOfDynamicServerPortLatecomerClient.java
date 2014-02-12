package port.sessionserver.relay.late.mvc.dynamic.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
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
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
