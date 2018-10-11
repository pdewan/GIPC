package sessionport.rpc.duplex.relayed.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import sessionport.datacomm.group.object.relayed.latecomer.ASessionPortLatecomerSupportingSessionServerLauncher;

public class DemoerOfDuplexRPCRelayedSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ASessionPortLatecomerSupportingSessionServerLauncher.class,
				AnAliceDuplexRPCRelayedSessionPort.class,
				ABobDuplexRPCRelayedSessionPort.class,
				AModularCathyDuplexRPCRelayedSessionPort.class,
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
