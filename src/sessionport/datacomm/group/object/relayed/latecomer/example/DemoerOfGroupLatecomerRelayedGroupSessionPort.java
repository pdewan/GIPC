package sessionport.datacomm.group.object.relayed.latecomer.example;

import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.ASessionServerRelayerLauncher;
import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import sessionport.datacomm.group.object.relayed.example.CathyClientRelayedGroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.ASessionPortLatecomerSupportingSessionServerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfGroupLatecomerRelayedGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ASessionPortLatecomerSupportingSessionServerLauncher.class,
				AliceMemberLatecomerGroupSessionPort.class,
				BobServerLatecomerGroupSessionPort.class,
				CathyClientRelayedGroupSessionPort.class, // cathy does no receive buffered messages as a client only
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
