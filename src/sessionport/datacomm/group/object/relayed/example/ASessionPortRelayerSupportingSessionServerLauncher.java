package sessionport.datacomm.group.object.relayed.example;

import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;



public class ASessionPortRelayerSupportingSessionServerLauncher extends ARelayerSupportingSessionServerLauncher {
	
	public ASessionPortRelayerSupportingSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
	}

	public static void main (String args[]) {	
		(new ASessionPortRelayerSupportingSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	
	
	
	
	
}
