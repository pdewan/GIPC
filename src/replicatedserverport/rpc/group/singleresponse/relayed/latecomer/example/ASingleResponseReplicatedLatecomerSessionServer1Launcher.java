package replicatedserverport.rpc.group.singleresponse.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ASingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server1Launcher;

public class ASingleResponseReplicatedLatecomerSessionServer1Launcher implements Server1Launcher{
	
	
	public static void main (String args[]) {
		(new ASingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
