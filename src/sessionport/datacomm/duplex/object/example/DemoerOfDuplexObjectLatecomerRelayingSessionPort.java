package sessionport.datacomm.duplex.object.example;

import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.pipe.MainClassLaunchingUtility;

public class DemoerOfDuplexObjectLatecomerRelayingSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ALatecomerSessionServerLauncher.class,
				AliceObjectDuplexSessionPort.class,
				BobObjectDuplexSessionPort.class,
				CathyObjectDuplexSessionPort.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
