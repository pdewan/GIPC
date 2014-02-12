package port.relay.mvc.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfTwoServerClient {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ARelayerLauncher.class,
				AGroupRPCServerCollaborativeMVCLauncher.class,
				AliceTwoServerClientLauncher.class,
				BobTwoServerClientLauncher.class
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
