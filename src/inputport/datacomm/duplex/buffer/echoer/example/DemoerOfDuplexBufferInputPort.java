package inputport.datacomm.duplex.buffer.echoer.example;

import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfDuplexBufferInputPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ADuplexBufferServerInputPortLauncher.class,
				AliceDuplexBufferInputPortLauncher.class,
				BobDuplexBufferInputPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
