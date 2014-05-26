package sessionport.datacomm.duplex.object.example;

import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.ASessionServerRelayerLauncher;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDuplexObjectLatecomerRelayingSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ALatecomerSessionServerLauncher.class,
				AnAliceObjectDuplexSessionPort.class,
				ABobObjectDuplexSessionPort.class,
				ACathyObjectDuplexSessionPort.class
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
