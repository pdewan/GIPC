package inputport.datacomm.simplex.object.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfSimplexObjectInputPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASimplexObjectServerInputPortLauncher.class,
				AnAliceSimplexObjectInputPortLauncher.class,
				ABobSimplexObjectInputPortLauncher.class
				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
