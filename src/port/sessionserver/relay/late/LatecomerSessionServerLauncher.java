package port.sessionserver.relay.late;

import inputport.InputPort;
import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;



public class LatecomerSessionServerLauncher extends ASessionServerLauncher {	
	public static void main (String args[]) {	
		(new LatecomerSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	public LatecomerSessionServerLauncher(String aSessionServerId, String aSessionServerName) {
		super(aSessionServerId, aSessionServerName );

	}
	public LatecomerSessionServerLauncher(String aSessionServerId, String aSessionServerName, String aLogicalServerName) {
		super(aSessionServerId, aSessionServerName, aLogicalServerName);
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ALatecomerServerLauncherSupport();

	}
	protected InputPort getPort() {
		return LatecomerRelayerAndSessionServerSelector.
		createLatecomerSessionsServerAndRelayer(sessionServerId, sessionServerName, logicalServerName);			
	}		
}
