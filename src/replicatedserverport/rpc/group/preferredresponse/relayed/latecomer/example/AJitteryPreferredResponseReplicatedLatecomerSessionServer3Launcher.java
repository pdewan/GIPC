package replicatedserverport.rpc.group.preferredresponse.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.example.AJitterySingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server3Launcher;

public class AJitteryPreferredResponseReplicatedLatecomerSessionServer3Launcher implements Server3Launcher {
	public static void main (String args[]) {
		(new AJitterySingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_3_DESCRIPTION)).launch();

	}
	


}
