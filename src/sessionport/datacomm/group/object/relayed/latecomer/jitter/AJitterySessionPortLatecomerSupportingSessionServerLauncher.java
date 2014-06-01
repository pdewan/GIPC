package sessionport.datacomm.group.object.relayed.latecomer.jitter;

import port.PortLauncherSupport;
import port.jitter.AJitterControllingPortLauncherSupport;
import sessionport.datacomm.group.object.relayed.latecomer.ASessionPortLatecomerSupportingSessionServerLauncher;



public class AJitterySessionPortLatecomerSupportingSessionServerLauncher extends ASessionPortLatecomerSupportingSessionServerLauncher {
	
	public static void main (String args[]) {	
		(new AJitterySessionPortLatecomerSupportingSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	public AJitterySessionPortLatecomerSupportingSessionServerLauncher(String aSessionServerId, String aSessionServerName) {
		super(aSessionServerId, aSessionServerName );

	}
	protected PortLauncherSupport getJitterPortLauncherSupport() {
			return new AJitterControllingPortLauncherSupport();
	}
	
	
}
