package inputport.rpc.group.mvc.collaborative.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

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
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
