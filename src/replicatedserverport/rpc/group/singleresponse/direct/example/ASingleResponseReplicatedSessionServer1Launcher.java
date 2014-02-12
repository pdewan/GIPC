package replicatedserverport.rpc.group.singleresponse.direct.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ASingleReponseReplicatedSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server1Launcher;

public class ASingleResponseReplicatedSessionServer1Launcher implements Server1Launcher{
	
	
	public static void main (String args[]) {
		(new ASingleReponseReplicatedSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
