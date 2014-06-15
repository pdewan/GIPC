package inputport.rpc.group.example;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfGroupRPCInputPort {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				AGroupRPCServerInputPortLauncher.class,
				AliceGroupRPCInputPortLauncher.class,
				BobGroupRPCInputPortLauncher.class,
				CathyGroupRPCInputPortLauncher.class,
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
