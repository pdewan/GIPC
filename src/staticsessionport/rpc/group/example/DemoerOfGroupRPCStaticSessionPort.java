package staticsessionport.rpc.group.example;

import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfGroupRPCStaticSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				AliceGroupRPCSSPLauncher.class, 
				BobGroupRPCSSPLauncher.class, 
				CathyGroupRPCSSPLauncher.class,

				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
