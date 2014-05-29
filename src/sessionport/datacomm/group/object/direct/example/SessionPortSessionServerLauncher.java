package sessionport.datacomm.group.object.direct.example;

import port.sessionserver.ASessionServerLauncher;



public class SessionPortSessionServerLauncher extends ASessionServerLauncher {
	
	public SessionPortSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
	}

	public static void main (String args[]) {	
		(new SessionPortSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	
	
	
	
	
}
