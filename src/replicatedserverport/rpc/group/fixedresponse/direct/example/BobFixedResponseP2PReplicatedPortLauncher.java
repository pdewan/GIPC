package replicatedserverport.rpc.group.fixedresponse.direct.example;

import inputport.datacomm.simplex.object.example.AliceClientLauncher;
import inputport.datacomm.simplex.object.example.BobClientLauncher;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import replicatedserverport.rpc.duplex.fixedresponse.ClientServerMapping;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server1Launcher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server2Launcher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class BobFixedResponseP2PReplicatedPortLauncher {
	public static void main(String[] args) {

		(new AFixedResponseSupportingFlexibleResponseReplicatedSessionPortLauncher(AFlexibleResponseReplicatedSessionPortLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, 
				BobClientLauncher.BOB_PORT, 
				BobClientLauncher.BOB,
				SessionChoice.P2P,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.ABobDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ReplicationChoice.LOCAL_RESPONSE,
				AFixedResponseSupportingFlexibleResponseReplicatedSessionPortLauncher.SERVERS_DESCRIPTION

				)).launch();
		
	}


}
