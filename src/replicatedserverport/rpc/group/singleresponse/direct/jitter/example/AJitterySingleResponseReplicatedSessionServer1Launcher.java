package replicatedserverport.rpc.group.singleresponse.direct.jitter.example;

import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.example.AJitterySingleReponseReplicatedSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server1Launcher;

public class AJitterySingleResponseReplicatedSessionServer1Launcher implements Server1Launcher{
	
	
	public static void main (String args[]) {
		(new AJitterySingleReponseReplicatedSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
