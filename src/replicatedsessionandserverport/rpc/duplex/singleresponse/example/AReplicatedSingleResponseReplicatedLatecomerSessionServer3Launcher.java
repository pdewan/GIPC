package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ASingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server3Launcher;

public class AReplicatedSingleResponseReplicatedLatecomerSessionServer3Launcher implements Server3Launcher {
	public static void main (String args[]) {
		(new ASingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_3_DESCRIPTION)).launch();

	}
	


}
