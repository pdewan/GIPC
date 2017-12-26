package replicatedsessionport.rpc.duplex.singleresponse.example;

import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.AFlexibleResponseReplicatedSessionPortLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import trace.port.ConnectionEventManagerFactory;
import bus.uigen.ObjectEditor;

public class AliceSingleResponseReplicatedSessionClientPortLauncher {

	public static void main (String[] args) {
		ObjectEditor.edit (ConnectionEventManagerFactory.getConnectionManager());
		
		(new ASingleResponseReplicatedGroupSessionPortClientLauncher(
				AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				ASingleResponseReplicatedGroupSessionPortClientLauncher.SERVER_NAME,

				"Alice",
				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				// for some reason this was null
//				AFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION, 

				null, 
				ASingleResponseReplicatedGroupSessionPortServerLauncher.DO_JITTER
				)).launch();
	}
	
	

}
