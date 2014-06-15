package replicatedserverport.rpc.group.fixedresponse.direct.example;

import inputport.datacomm.simplex.object.example.BobClientLauncher;
import inputport.datacomm.simplex.object.example.CathyClientLauncher;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.duplex.fixedresponse.ClientServerMapping;
import replicatedserverport.rpc.group.flexibleresponse.flexible.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.ReplicationChoice;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server2Launcher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server3Launcher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class CathyFixedResponseP2PReplicatedPortLauncher {
	public static void main(String[] args) {

		(new AFixedResponseSupportingFlexibleResponseReplicatedSessionPortLauncher(AFlexibleResponseReplicatedSessionPortLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				CathyClientLauncher.CATHY_PORT, 
				CathyClientLauncher.CATHY,
				SessionChoice.P2P,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.ACathyDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ReplicationChoice.LOCAL_RESPONSE,
				AFixedResponseSupportingFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION

				)).launch();
		
	}


}
