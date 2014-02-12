package replicatedserverport.rpc.group.singleresponse.direct.jitter.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import port.trace.ConnectionEventManagerFactory;
import replicatedserverport.rpc.group.flexibleresponse.flexibejitter.example.AJitteryFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;
import bus.uigen.ObjectEditor;

public class AnAliceJitterySingleResponseP2PReplicatedPortLauncher {
	public static void main(String[] args) {
		ObjectEditor.edit (ConnectionEventManagerFactory.getConnectionManager());
//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Bob", 100);
//		delayManager.setMinimumDelay("Cathy", 5000);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant( "9100", "Alice", false, false, false);		
		(new AJitteryFlexibleResponseReplicatedSessionPortLauncher(AFlexibleResponseReplicatedSessionPortLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, "9100", 
				"Alice",
				SessionChoice.P2P,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ReplicationChoice.SINGLE_RESPONSE,
				ParticipantChoice.MEMBER,

				AFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION,
				true

				)).launch();
		
	}


}
