package replicatedsessionport.rpc.duplex.singleresponse.example;

import inputport.InputPort;
import port.PortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.late.ALatecomerServerLauncherSupport;
import port.sessionserver.relay.late.LatecomerRelayerAndSessionServerSelector;



public class ALatecomerSessionServerLauncher extends ASessionServerLauncher {
	
	public static void main (String args[]) {	
		(new ALatecomerSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	public ALatecomerSessionServerLauncher(String aSessionServerId, String aSessionServerName) {
		super(aSessionServerId, aSessionServerName );
//		sessionServerName = aSessionServerName;
//		sessionServerId = aSessionServerId;
	}
	public ALatecomerSessionServerLauncher(String aSessionServerId, String aSessionServerName, String aLogicalServerName) {
		super(aSessionServerId, aSessionServerName, aLogicalServerName);
	}
	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ARelayServerLauncherSupport();
		return new ALatecomerServerLauncherSupport();

	}
	@Override
	protected InputPort getPort() {
		return LatecomerRelayerAndSessionServerSelector.
		createLatecomerSessionsServerAndRelayer(sessionServerId, sessionServerName, logicalServerName);
			
	}


	
	
	
}
