package inputport.rpc.duplex.mvc.singleuser.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDuplexRPCInputPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ADuplexRPCServerMVCLauncher.class,
				ADuplexRPCClientMVCLauncher.class				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
