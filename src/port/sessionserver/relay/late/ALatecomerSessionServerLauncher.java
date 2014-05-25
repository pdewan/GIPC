package port.sessionserver.relay.late;

import inputport.InputPort;
import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;



public class ALatecomerSessionServerLauncher extends ASessionServerLauncher {	
	public static void main (String args[]) {	
		(new ALatecomerSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	public ALatecomerSessionServerLauncher(String aSessionServerId, String aSessionServerName) {
		super(aSessionServerId, aSessionServerName );

	}
	public ALatecomerSessionServerLauncher(String aSessionServerId, String aSessionServerName, String aLogicalServerName) {
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
