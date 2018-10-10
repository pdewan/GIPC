package sessionport.rpc.group.direct.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.ASessionServerLauncher;

public class ModularDemoerOfGroupRPCDirectSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ASessionServerLauncher.class,
				AModularAliceGroupRPCDirectSessionPort.class,
				AModularBobGroupRPCDirectSessionPort.class,
				AModularCathyGroupRPCDirectSessionPort.class,
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
