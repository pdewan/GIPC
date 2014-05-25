package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.trace.TraceableDisplayAndWaitManagerFactory;

public class AReplicatedServer2SingleResponseGroupSessionServerPortLauncher  {
	public static String SERVER = "Server 2";

	public static void main (String[] args) {
//		OEFrame frame = ObjectEditor.edit(ConnectionEventManagerFactory.getConnectionManager());
		OEFrame frame = ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());

		frame.setTitle( SERVER);
		
		(new AReplicatedSingleResponseReplicatedGroupSessionServerPortServerLauncher(
				AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				"9201", 
				SERVER ,
//				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				SessionChoice.LATECOMER_RELAYED,

				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(), // has to be changed
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				AFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION, 

				AReplicatedSingleResponseReplicatedGroupSessionServerPortServerLauncher.DO_JITTER
				)).launch();
	}
	
	

}
