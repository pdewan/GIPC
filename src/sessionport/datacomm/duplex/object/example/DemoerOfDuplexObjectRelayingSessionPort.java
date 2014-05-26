package sessionport.datacomm.duplex.object.example;

import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.ASessionServerRelayerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDuplexObjectRelayingSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ARelayerSupportingSessionServerLauncher.class,
				ASessionServerRelayerLauncher.class,
				AnAliceObjectDuplexSessionPort.class,
				ABobObjectDuplexSessionPort.class,
				ACathyObjectDuplexSessionPort.class
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
