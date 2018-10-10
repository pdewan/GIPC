package sessionport.rpc.duplex.direct.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.ASessionServerLauncher;

public class DemoerOfDuplexRPCDirectSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ASessionServerLauncher.class,
				AnAliceDuplexRPCDirectSessionPort.class,
				ABobDuplexRPCDirectSessionPort.class,
				AModularCathyDuplexRPCDirectSessionPort.class,
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
