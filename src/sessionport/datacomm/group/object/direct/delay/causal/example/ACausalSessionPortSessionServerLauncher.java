package sessionport.datacomm.group.object.direct.delay.causal.example;

import port.sessionserver.ASessionServerLauncher;



public class ACausalSessionPortSessionServerLauncher extends ASessionServerLauncher {
	
	public ACausalSessionPortSessionServerLauncher(String aSessionServerId,
			String aSessionServerName) {
		super(aSessionServerId, aSessionServerName);
	}

	public static void main (String args[]) {	
		(new ACausalSessionPortSessionServerLauncher("" + SESSION_SERVER_PORT, SESSION_SERVER_NAME)).launch();
	}
	
	
	
	
	
}
