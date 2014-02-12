package inputport.rpc.duplex.mvc.multiuser.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfMultiiUserDuplexRPCInputPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				AMultiUserDuplexRPCServerMVCLauncher.class,
				AliceDuplexRPCClientMVCLauncher.class,	
				BobDuplexRPCClientMVCLauncher.class	
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
}
