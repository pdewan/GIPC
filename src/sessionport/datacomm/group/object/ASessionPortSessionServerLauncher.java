package sessionport.datacomm.group.object;

import port.sessionserver.ASessionServerLauncher;


// this class adds no functionality and is not used
public class ASessionPortSessionServerLauncher extends ASessionServerLauncher {
	
	public ASessionPortSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
	}

	public static void main (String args[]) {	
		(new ASessionPortSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	
	
	
	
	
}
