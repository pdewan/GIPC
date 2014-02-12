package sessionport.datacomm.group.object.direct.delay.jitter.example;

import port.PortLauncherSupport;
import port.jitter.AJitterControllingPortLauncherSupport;
import port.sessionserver.ASessionServerLauncher;



public class AJitterySessionPortSessionServerLauncher extends ASessionServerLauncher {
	
	public AJitterySessionPortSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
	}

	public static void main (String args[]) {	
		(new AJitterySessionPortSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	
	protected PortLauncherSupport getJitterPortLauncherSupport() {
		return new AJitterControllingPortLauncherSupport();
    }
	
	
}
