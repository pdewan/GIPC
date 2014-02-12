package inputport.rpc.duplex.counter.example;

import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfCounter {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ADuplexCounterServerLauncher.class,
				ADuplexCounterClientLauncher.class	
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
}
