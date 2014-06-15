package replicatedserverport.rpc.group.singleresponse.direct.jitter.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ASingleReponseReplicatedSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server3Launcher;

public class AJitterySingleResponseReplicatedSessionServer3Launcher implements Server3Launcher {
	public static void main (String args[]) {
		(new ASingleReponseReplicatedSessionServerLauncher(SERVER_3_DESCRIPTION)).launch();

	}
	


}
