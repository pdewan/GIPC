package sessionport.datacomm.group.object.relayed.example;

import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.example.ASessionServerRelayerLauncher;



public class ASessionPortRelayerLauncher extends ASessionServerRelayerLauncher {
	

	

	public static void main (String args[]) {	
//		(new ARelayerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
		(new ASessionPortRelayerLauncher(RELAYER_ID, RELAYER_NAME, SESSION_SERVER_HOST, ASessionServerLauncher.SESSION_SERVER_ID, ASessionServerLauncher.SESSION_SERVER_NAME)).launch();

	}
	public  ASessionPortRelayerLauncher (String aRelayerId, String aRelayerName, String aSessionServerHost, String aSessionServerId, String aSessionServerName) {
		super(aRelayerId, aRelayerName, aSessionServerHost, aSessionServerId, aSessionServerName);
	}

	
	
}
