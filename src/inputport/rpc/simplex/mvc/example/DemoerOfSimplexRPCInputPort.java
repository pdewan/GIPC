package inputport.rpc.simplex.mvc.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfSimplexRPCInputPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ASimplexRPCServerMVCLauncher.class,
				ASimplexRPCClientMVCLauncher.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
