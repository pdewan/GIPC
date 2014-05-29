package port.sessionserver.relay;

import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;



public class RelayerSupportingSessionServerLauncher extends ASessionServerLauncher {	
	public static void main (String args[]) {	
		(new RelayerSupportingSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	public RelayerSupportingSessionServerLauncher(String aSessionServerId, String aSessionServerName) {
		super(aSessionServerId, aSessionServerName );
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASessionServerRelayerLauncherSupport();
	}	
}
