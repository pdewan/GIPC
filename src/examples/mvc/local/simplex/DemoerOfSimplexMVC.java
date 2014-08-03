package examples.mvc.local.simplex;

import bus.uigen.pipes.MainClassLaunchingUtility;

public class DemoerOfSimplexMVC {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
		
		Class[] classes = {
				ASimplexFrostyLauncher.class				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
