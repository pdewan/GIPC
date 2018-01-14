package replicatedserverport.rpc.group.singleresponse.relayed.latecomer.jitter.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.AJitteryFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.ReplicationChoice;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import util.trace.port.ConnectionEventManagerFactory;
import bus.uigen.ObjectEditor;

public class BobJitterySingleResponseLatecomerReplicatedPortLauncher {
	public static void main(String[] args) {
		ObjectEditor.edit(ConnectionEventManagerFactory.getConnectionManager());

//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Bob", 100);
//		delayManager.setMinimumDelay("Cathy", 5000);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant( "9100", "Alice", false, false, false);		
		(new AJitteryFlexibleResponseReplicatedSessionPortLauncher(AFlexibleResponseReplicatedSessionPortLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, "9101", 
				"Bob",
				SessionChoice.LATECOMER_RELAYED,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.ABobDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ReplicationChoice.SINGLE_RESPONSE,
				ParticipantChoice.MEMBER,

				AFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION,
				true

				)).launch();
		
	}


}
