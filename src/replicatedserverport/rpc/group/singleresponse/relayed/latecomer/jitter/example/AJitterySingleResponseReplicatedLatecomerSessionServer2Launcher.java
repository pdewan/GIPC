package replicatedserverport.rpc.group.singleresponse.relayed.latecomer.jitter.example;

import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.example.AJitterySingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server2Launcher;

public class AJitterySingleResponseReplicatedLatecomerSessionServer2Launcher implements Server2Launcher{
	public static void main (String args[]) {
		(new AJitterySingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();

	}
	


}
