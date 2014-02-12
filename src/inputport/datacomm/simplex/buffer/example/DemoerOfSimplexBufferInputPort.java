package inputport.datacomm.simplex.buffer.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfSimplexBufferInputPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASimplexBufferServerInputPortLauncher.class,
				AnAliceSimplexBufferInputPortLauncher.class,
				ABobSimplexBufferInputPortLauncher.class
				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
