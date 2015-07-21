package sessionport.rpc.duplex.relayed.example;

import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.ASessionServerRelayerLauncher;
import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import sessionport.datacomm.group.object.relayed.example.CathyClientRelayedGroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.ASessionPortLatecomerSupportingSessionServerLauncher;
import sessionport.datacomm.group.object.relayed.latecomer.example.AliceMemberLatecomerGroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.example.BobServerLatecomerGroupSessionPort;
import bus.uigen.pipe.MainClassLaunchingUtility;

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
