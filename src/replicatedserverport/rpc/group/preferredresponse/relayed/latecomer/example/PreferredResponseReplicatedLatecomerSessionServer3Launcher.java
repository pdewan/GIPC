package replicatedserverport.rpc.group.preferredresponse.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.AJitterySingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server3Launcher;

public class PreferredResponseReplicatedLatecomerSessionServer3Launcher implements Server3Launcher {
	public static void main (String args[]) {
		(new AJitterySingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_3_DESCRIPTION)).launch();

	}
	


}
