package sessionport.datacomm.group.object.direct.example;

import port.sessionserver.ASessionServerLauncher;



public class ASessionPortSessionServerLauncher extends ASessionServerLauncher {
	
	public ASessionPortSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
	}

	public static void main (String args[]) {	
		(new ASessionPortSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	
	
	
	
	
}
