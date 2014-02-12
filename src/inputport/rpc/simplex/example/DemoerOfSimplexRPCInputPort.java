package inputport.rpc.simplex.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfSimplexRPCInputPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASimplexRPCServerInputPortLauncher.class,
				AliceSimplexRPCInputPortLauncher.class,
				BobSimplexRPCInputPortLauncher.class
				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
