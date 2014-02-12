package port.sessionserver.relay;

import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;



public class ARelayerSupportingSessionServerLauncher extends ASessionServerLauncher {	
	public static void main (String args[]) {	
		(new ARelayerSupportingSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	public ARelayerSupportingSessionServerLauncher(String aSessionServerId, String aSessionServerName) {
		super(aSessionServerId, aSessionServerName );
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASessionServerRelayerLauncherSupport();
	}	
}
