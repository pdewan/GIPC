package multiserverport.rpc.group.example;

import staticsessionport.rpc.group.example.AliceGroupRPCSSPLauncher;
import staticsessionport.rpc.group.example.BobGroupRPCSSPLauncher;
import staticsessionport.rpc.group.example.CathyGroupRPCSSPLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfGroupRPCMultiServerPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {

				AliceGroupRPCSSPLauncher.class, 
				BobGroupRPCSSPLauncher.class, 
				CathyGroupRPCSSPLauncher.class,

				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
