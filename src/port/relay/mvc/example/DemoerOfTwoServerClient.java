package port.relay.mvc.example;

import bus.uigen.pipes.MainClassLaunchingUtility;

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
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
