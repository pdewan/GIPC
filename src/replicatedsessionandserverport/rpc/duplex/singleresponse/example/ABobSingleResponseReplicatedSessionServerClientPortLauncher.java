package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.trace.TraceableDisplayAndWaitManagerFactory;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedsessionport.rpc.duplex.singleresponse.example.ASingleResponseReplicatedGroupSessionPortClientLauncher;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;
import util.trace.Tracer;

public class ABobSingleResponseReplicatedSessionServerClientPortLauncher {
	public static String USER_NAME  = "Bob";

	public static void main (String[] args) {
//		Tracer.showInfo(false);
//		OEFrame frame = ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());
//		frame.setTitle(USER_NAME);
		(new ASingleResponseReplicatedGroupSessionPortClientLauncher(
				AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				ASingleResponseReplicatedGroupSessionPortClientLauncher.SERVER_NAME,

				USER_NAME,
//				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				SessionChoice.LATECOMER_RELAYED,

				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.ABobDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				AFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION, AReplicatedSingleResponseReplicatedGroupSessionServerPortServerLauncher.DO_JITTER
				)).launch();
	}
	
	

}
