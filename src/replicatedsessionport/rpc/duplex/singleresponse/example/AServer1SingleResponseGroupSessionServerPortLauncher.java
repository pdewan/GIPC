package replicatedsessionport.rpc.duplex.singleresponse.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.duplex.singleresponse.example.GroupRPCServer1Launcher;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;
import sessionport.datacomm.group.object.flexible.jitter.example.AJitteryFlexibleSessionPortClientLauncher;

public class AServer1SingleResponseGroupSessionServerPortLauncher  {

	public static void main (String[] args) {
		
		(new ASingleResponseReplicatedGroupSessionPortServerLauncher(
				AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, 
				"9200", 
				"Server 1",
				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ASingleResponseReplicatedGroupSessionPortServerLauncher.DO_JITTER
				)).launch();
	}
	
	

}
