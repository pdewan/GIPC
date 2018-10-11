package sessionport.datacomm.duplex.object.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;

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
