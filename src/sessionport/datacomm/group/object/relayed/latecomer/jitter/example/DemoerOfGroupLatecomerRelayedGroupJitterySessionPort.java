package sessionport.datacomm.group.object.relayed.latecomer.jitter.example;

import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.ASessionServerRelayerLauncher;
import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import sessionport.datacomm.group.object.relayed.example.CathyClientRelayedGroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.ASessionPortLatecomerSupportingSessionServerLauncher;
import sessionport.datacomm.group.object.relayed.latecomer.jitter.AJitterySessionPortLatecomerSupportingSessionServerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.pipes.MainClassLaunchingUtility;
// Object editor ofren hangs when interacting with Alice, maybe ecause of new layout of console or setting of cursor
// Have put tracing on in Alice launcher but could not reproduce problem with it
public class DemoerOfGroupLatecomerRelayedGroupJitterySessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				AJitterySessionPortLatecomerSupportingSessionServerLauncher.class,
				AliceJitteryLatecomerGroupSessionPortLauncher.class,
				BobJitteryLatecomerGroupSessionPortLauncher.class,
				CathyJitteryLateGroupSessionPortLauncher.class, 
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
