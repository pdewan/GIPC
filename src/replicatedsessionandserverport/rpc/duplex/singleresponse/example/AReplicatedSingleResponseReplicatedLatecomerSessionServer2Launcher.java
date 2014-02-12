package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ASingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server2Launcher;

public class AReplicatedSingleResponseReplicatedLatecomerSessionServer2Launcher implements Server2Launcher{
	public static void main (String args[]) {
		(new ASingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();

	}
	


}
