package inputport.datacomm.simplex.buffer.mvc.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfMVCSimplexBufferInputPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASimplexBufferServerMVCLauncher.class,
				ASimplexBufferClientMVCLauncher.class,
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
