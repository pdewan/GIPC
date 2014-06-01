package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.trace.TraceableDisplayAndWaitManagerFactory;

public class AReplicatedServer1SingleResponseGroupSessionServerPortLauncher  {
	
	public static String SERVER = "Server 1";

	public static void main (String[] args) {
//		ObjectEditor.edit (ConnectionEventManagerFactory.getConnectionManager());
		OEFrame frame = ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());
		frame.setTitle(SERVER);
		
		(new AReplicatedSingleResponseReplicatedGroupSessionServerPortServerLauncher(
				AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				ASessionServerLauncher.SESSION_SERVER_NAME, // this will be the logical name
				ASessionServerLauncher.SESSION_SERVER_NAME, // this will be ignored
				"9200", 
				SERVER,
//				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				SessionChoice.LATECOMER_RELAYED,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				AFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION, 

				AReplicatedSingleResponseReplicatedGroupSessionServerPortServerLauncher.DO_JITTER
				)).launch();
	}
	
	

}
