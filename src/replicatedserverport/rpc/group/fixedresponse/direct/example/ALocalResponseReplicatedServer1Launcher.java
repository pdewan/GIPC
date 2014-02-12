package replicatedserverport.rpc.group.fixedresponse.direct.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server1Launcher;

public class ALocalResponseReplicatedServer1Launcher implements Server1Launcher{
	
	
	public static void main (String args[]) {
		(new ALocalResponseReplicatedSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
