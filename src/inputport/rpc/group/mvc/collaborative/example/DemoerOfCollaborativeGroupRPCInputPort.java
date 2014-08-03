package inputport.rpc.group.mvc.collaborative.example;

import bus.uigen.pipes.MainClassLaunchingUtility;

public class DemoerOfCollaborativeGroupRPCInputPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				AGroupRPCServerCollaborativeMVCLauncher.class,
				AliceCollaborativeDuplexRPCClientMVCLauncher.class,
				BobCollaborativeDuplexRPCClientMVCLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
