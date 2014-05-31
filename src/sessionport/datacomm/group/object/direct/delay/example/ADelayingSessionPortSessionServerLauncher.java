package sessionport.datacomm.group.object.direct.delay.example;

import port.sessionserver.ASessionServerLauncher;


// do not need this class
public class ADelayingSessionPortSessionServerLauncher extends ASessionServerLauncher {
	
	public ADelayingSessionPortSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
	}

	public static void main (String args[]) {	
		(new ADelayingSessionPortSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	
	
	
	
	
}
