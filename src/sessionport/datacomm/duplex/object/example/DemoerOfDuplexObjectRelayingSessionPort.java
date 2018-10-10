package sessionport.datacomm.duplex.object.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;

public class DemoerOfDuplexObjectRelayingSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				ARelayerSupportingSessionServerLauncher.class,
				SessionServerRelayerLauncher.class, // seems that a relayer is not automatically registered with the session server
				AliceObjectDuplexSessionPort.class,
				BobObjectDuplexSessionPort.class,
				CathyObjectDuplexSessionPort.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
