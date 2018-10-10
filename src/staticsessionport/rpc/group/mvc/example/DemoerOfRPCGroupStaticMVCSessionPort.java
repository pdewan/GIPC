package staticsessionport.rpc.group.mvc.example;

import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfRPCGroupStaticMVCSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {

				MVCServerStaticSessionPortLauncher.class, 
				AliceMVCStaticSessionPortLauncher.class, 
				BobMVCStaticSessionPortLauncher.class,
				CathyMVCStaticSessionPortLauncher.class

				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
