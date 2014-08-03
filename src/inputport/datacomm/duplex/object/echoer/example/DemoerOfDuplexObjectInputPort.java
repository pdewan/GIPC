package inputport.datacomm.duplex.object.echoer.example;

import bus.uigen.pipes.MainClassLaunchingUtility;

public class DemoerOfDuplexObjectInputPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ADuplexObjectServerInputPortLauncher.class,
				AliceDuplexObjectInputPortLauncher.class,
				BobDuplexObjectInputPortLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
