package replicatedserverport.rpc.group.fixedresponse.direct.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server2Launcher;

public class ALocalResponseReplicatedServer2Launcher implements Server2Launcher{
	
	
	public static void main (String args[]) {
		(new ALocalResponseReplicatedSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();

	}
	

	

}
