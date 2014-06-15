package inputport.rpc.group.mvc.collaborative.relaying.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfRelayingCollaborativeGroupRPCInputPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				AGroupRPCServerRelayingCollaborativeMVCLauncher.class,
				AliceRelayingCollaborativeDuplexRPCClientMVCLauncher.class,
				BobRelayingCollaborativeDuplexRPCClientMVCLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
