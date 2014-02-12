package replicatedserverport.rpc.group.preferredresponse.relayed.latecomer.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;
import replicatedserverport.rpc.group.moreflexibleresponse.flexibe.jitter.example.AJitteryMoreFlexibleResponseReplicatedSessionPortLauncher;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;

public class ABobJitteryPreferredResponseLatecomerReplicatedPortLauncher {
	public static void main(String[] args) {
//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Bob", 100);
//		delayManager.setMinimumDelay("Cathy", 5000);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant( "9100", "Alice", false, false, false);		
		(new AJitteryMoreFlexibleResponseReplicatedSessionPortLauncher(AFlexibleResponseReplicatedSessionPortLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, "9101", 
				"Bob",
				SessionChoice.LATECOMER_RELAYED,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.ABobDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ReplicationChoice.PREFERRED_RESPONSE,
				ParticipantChoice.MEMBER,
				AFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION, true

				)).launch();
		
	}


}
