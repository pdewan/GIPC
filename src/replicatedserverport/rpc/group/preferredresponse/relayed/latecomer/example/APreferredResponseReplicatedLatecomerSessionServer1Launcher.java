package replicatedserverport.rpc.group.preferredresponse.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.AJitterySingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server1Launcher;

public class APreferredResponseReplicatedLatecomerSessionServer1Launcher implements Server1Launcher{
	// Not handling jitter to th
	
	public static void main (String args[]) {
		(new AJitterySingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
