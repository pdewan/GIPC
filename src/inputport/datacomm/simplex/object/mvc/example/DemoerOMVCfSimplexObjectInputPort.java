package inputport.datacomm.simplex.object.mvc.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOMVCfSimplexObjectInputPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				ASimplexObjectServerMVCLauncher.class,
				ASimplexObjectClientMVCLauncher.class,
				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}
