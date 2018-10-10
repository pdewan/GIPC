package sessionport.datacomm.group.object.relayed.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.ASessionServerRelayerLauncher;
/*
 * semantics are the same for the p2p version as the server does not create an input loop
 */
public class DemoerOfGroupRelayedGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ARelayerSupportingSessionServerLauncher.class,
				ASessionServerRelayerLauncher.class,
				AliceMemberRelayedGroupSessionPort.class,
				BobServerRelayedGroupSessionPort.class,
				CathyClientRelayedGroupSessionPort.class, // cathy can receive changes even though she is a client only, with session ports the behcior is different from session server

		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
