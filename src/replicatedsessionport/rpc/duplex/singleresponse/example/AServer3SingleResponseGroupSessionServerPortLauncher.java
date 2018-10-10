package replicatedsessionport.rpc.duplex.singleresponse.example;

import bus.uigen.ObjectEditor;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import util.trace.port.ConnectionEventManagerFactory;

public class AServer3SingleResponseGroupSessionServerPortLauncher  {

	public static void main (String[] args) {
		ObjectEditor.edit(ConnectionEventManagerFactory.getConnectionManager());
		
		(new ASingleResponseReplicatedGroupSessionPortServerLauncher(
				AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, 
				"9202", 
				"Server 3",
				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(), // has to be changed
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ASingleResponseReplicatedGroupSessionPortServerLauncher.DO_JITTER
				)).launch();
	}
	
	

}
