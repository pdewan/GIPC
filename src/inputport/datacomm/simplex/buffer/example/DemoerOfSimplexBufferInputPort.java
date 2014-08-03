package inputport.datacomm.simplex.buffer.example;

import bus.uigen.pipes.MainClassLaunchingUtility;

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
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
