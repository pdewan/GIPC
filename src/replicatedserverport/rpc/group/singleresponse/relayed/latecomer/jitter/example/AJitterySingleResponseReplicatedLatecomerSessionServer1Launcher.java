package replicatedserverport.rpc.group.singleresponse.relayed.latecomer.jitter.example;

import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.example.AJitterySingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server1Launcher;

public class AJitterySingleResponseReplicatedLatecomerSessionServer1Launcher implements Server1Launcher{
	// Not handling jitter to th
	
	public static void main (String args[]) {
		(new AJitterySingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
