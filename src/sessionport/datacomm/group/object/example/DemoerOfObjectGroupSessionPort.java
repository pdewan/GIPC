package sessionport.datacomm.group.object.example;

import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;
// the words of robert forst are misleading, this program does not accept any input
// acting only at connection time
public class DemoerOfObjectGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ALatecomerSessionServerLauncher.class,

				AliceObjectGroupSessionPort.class,
				BobObjectGroupSessionPort.class,
				CathyObjectGroupSessionPort.class 
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
