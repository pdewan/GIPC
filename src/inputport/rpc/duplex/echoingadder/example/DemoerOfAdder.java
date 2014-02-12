package inputport.rpc.duplex.echoingadder.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfAdder {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ADuplexAdderServerLauncher.class,
				ADuplexAdderClientLauncher.class	
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
}
