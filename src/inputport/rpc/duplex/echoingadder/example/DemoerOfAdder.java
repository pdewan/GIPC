package inputport.rpc.duplex.echoingadder.example;

import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfAdder {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ADuplexAdderServerLauncher.class,
				ADuplexAdderClientLauncher.class	
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
}
