package replicatedserverport.rpc.group.fixedresponse.direct.example;

import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import inputport.datacomm.simplex.object.example.BobClientLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;

public class ABobFixedResponseP2PReplicatedPortLauncher {
	public static void main(String[] args) {
//		DelayManager delayManager = GlobalState.getDelayManager();
//		delayManager.setMinimumDelay("Bob", 100);
//		delayManager.setMinimumDelay("Cathy", 5000);
//		AnOldLatecomerObjectGroupSessionPortLauncher.launchSessionPartipant( "9100", "Alice", false, false, false);		
		(new AMoreFlexibleResponseReplicatedSessionPortLauncher(AFlexibleResponseReplicatedSessionPortLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				BobClientLauncher.BOB_PORT, 
				BobClientLauncher.BOB,
				SessionChoice.P2P,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.ABobDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ReplicationChoice.LOCAL_RESPONSE,
				AMoreFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION

				)).launch();
		
	}


}
