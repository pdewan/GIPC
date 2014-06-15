package replicatedserverport.rpc.group.fixedresponse.direct.example;

import inputport.datacomm.simplex.object.example.AliceClientLauncher;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.duplex.fixedresponse.ClientServerMapping;
import replicatedserverport.rpc.group.flexibleresponse.flexible.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.ReplicationChoice;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server1Launcher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class AliceFixedResponseP2PReplicatedPortLauncher {
	public static void main(String[] args) {
		(new AFixedResponseSupportingFlexibleResponseReplicatedSessionPortLauncher(AFlexibleResponseReplicatedSessionPortLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				AliceClientLauncher.ALICE_PORT, 
				AliceClientLauncher.ALICE,
				SessionChoice.P2P,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ReplicationChoice.LOCAL_RESPONSE,
				AFixedResponseSupportingFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION

				)).launch();
		
	}


}
