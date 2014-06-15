package replicatedserverport.rpc.group.singleresponse.direct.jitter.example;

import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.AJitterySingleReponseReplicatedSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server2Launcher;

public class AJitterySingleResponseReplicatedSessionServer2Launcher implements Server2Launcher{
	public static void main (String args[]) {
		(new AJitterySingleReponseReplicatedSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();

	}
	


}
