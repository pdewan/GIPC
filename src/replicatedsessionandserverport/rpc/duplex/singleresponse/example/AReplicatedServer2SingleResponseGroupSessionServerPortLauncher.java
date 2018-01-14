package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.AFlexibleResponseReplicatedSessionPortLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.datacomm.group.object.relayed.latecomer.ALatecomerRelayingGroupConnectionsManager;
import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;

public class AReplicatedServer2SingleResponseGroupSessionServerPortLauncher  {
	public static String SERVER = "Server 2";

	public static void main (String[] args) {
		
		// for some reason this server was not catching up and playing messages
		// hence the tracing
//		Tracer.showInfo(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setKeywordPrintStatus(ALatecomerRelayingGroupConnectionsManager.class, true);
		// the displaying of the frame may have something to do with things hanging
//		OEFrame frame = ObjectEditor.edit(ConnectionEventManagerFactory.getConnectionManager());
//		OEFrame frame = ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());

//		frame.setTitle( SERVER);
		
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
