package replicatedserverport.rpc.group.singleresponse.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ASingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server2Launcher;

public class ASingleResponseReplicatedLatecomerSessionServer2Launcher implements Server2Launcher{
	public static void main (String args[]) {
		(new ASingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();

	}
	


}
