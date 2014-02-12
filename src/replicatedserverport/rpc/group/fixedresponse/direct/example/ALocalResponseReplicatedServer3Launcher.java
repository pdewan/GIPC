package replicatedserverport.rpc.group.fixedresponse.direct.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server3Launcher;

public class ALocalResponseReplicatedServer3Launcher implements Server3Launcher{
	
	
	public static void main (String args[]) {
		(new ALocalResponseReplicatedSessionServerLauncher(SERVER_3_DESCRIPTION)).launch();

	}
	

	

}
