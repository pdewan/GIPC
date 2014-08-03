package inputport.rpc.simplex.example;

import bus.uigen.pipe.MainClassLaunchingUtility;

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
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
