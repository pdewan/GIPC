package replicatedsessionport.rpc.duplex.singleresponse.example;

import bus.uigen.ObjectEditor;
import port.sessionserver.ASessionServerLauncher;
import port.trace.ConnectionEventManagerFactory;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;

public class AnAliceSingleResponseReplicatedSessionClientPortLauncher {

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
				null, 
				ASingleResponseReplicatedGroupSessionPortServerLauncher.DO_JITTER
				)).launch();
	}
	
	

}
