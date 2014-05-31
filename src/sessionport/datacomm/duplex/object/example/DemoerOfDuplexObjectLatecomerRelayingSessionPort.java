package sessionport.datacomm.duplex.object.example;

import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import port.sessionserver.relay.late.LatecomerSessionServerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDuplexObjectLatecomerRelayingSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				LatecomerSessionServerLauncher.class,
				AliceObjectDuplexSessionPort.class,
				BobObjectDuplexSessionPort.class,
				CathyObjectDuplexSessionPort.class
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
