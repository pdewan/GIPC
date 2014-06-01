package replicatedsessionport.rpc.duplex.singleresponse.example;

import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

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
