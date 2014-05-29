package sessionport.datacomm.group.object.direct.example;

import port.sessionserver.relay.RelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfGroupObjectP2PSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				SessionPortSessionServerLauncher.class,
				AliceP2PMemberGroupSessionPort.class,
				BobP2PServerOnlyGroupSessionPort.class,
				CathyP2PClientGroupSessionPort.class
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
