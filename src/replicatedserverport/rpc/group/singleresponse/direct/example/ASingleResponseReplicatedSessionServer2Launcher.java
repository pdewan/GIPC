package replicatedserverport.rpc.group.singleresponse.direct.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ASingleReponseReplicatedSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server2Launcher;

public class ASingleResponseReplicatedSessionServer2Launcher implements Server2Launcher{
	public static void main (String args[]) {
		(new ASingleReponseReplicatedSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();

	}
	


}
