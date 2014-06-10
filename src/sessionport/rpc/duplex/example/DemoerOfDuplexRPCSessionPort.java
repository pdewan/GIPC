package sessionport.rpc.duplex.example;

import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.ASessionServerRelayerLauncher;
import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import sessionport.datacomm.group.object.relayed.example.CathyClientRelayedGroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.ASessionPortLatecomerSupportingSessionServerLauncher;
import sessionport.datacomm.group.object.relayed.latecomer.example.AliceMemberLatecomerGroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.example.BobServerLatecomerGroupSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;
// this is old stuff, need some kind of add method
// Need to inheit the lanunchers otherwise the relayer proxy does not work
// have the old and new stuff working as modular and non modular versions

public class DemoerOfDuplexRPCSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ASessionPortLatecomerSupportingSessionServerLauncher.class,
				AnAliceDuplexRPCSessionPort.class,
				ABobDuplexRPCSessionPort.class,
				AModularCathyDuplexRPCSessionPort.class,
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
