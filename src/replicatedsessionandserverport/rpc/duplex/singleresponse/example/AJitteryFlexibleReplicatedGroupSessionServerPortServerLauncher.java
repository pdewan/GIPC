package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.ConnectionListener;
import inputport.InputPort;
import replicatedserverport.rpc.duplex.singleresponse.ARelayedSingleResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ReplicationChoice;
import replicatedserverport.rpc.group.moreflexibleresponse.flexibe.jitter.example.AJitteryMoreFlexibleResponseReplicatedSessionPortLauncher;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseReplicatedGroupServerLauncherSupport;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseReplicatedRelayedGroupServerLauncherSupport;
import replicatedsessionport.rpc.duplex.ReplicatedSessionDuplexRPCPortSelector;
import sessionport.datacomm.group.object.flexible.jitter.example.AJitteryFlexibleSessionPortClientLauncher;

public class AJitteryFlexibleReplicatedGroupSessionServerPortServerLauncher 
	extends AJitteryMoreFlexibleResponseReplicatedSessionPortLauncher{ // now this should inherit from the replicated choice

	public AJitteryFlexibleReplicatedGroupSessionServerPortServerLauncher(
			String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal,
			ReplicationChoice aReplicationChoice,
			SessionParticipantDescription[] aServersDescription,
			boolean aDoJitter) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, aReplicationChoice, ParticipantChoice.SERVER_ONLY, aServersDescription, aDoJitter);
	}
	

	@Override
	protected ConnectionListener getConnectionListener(InputPort anInputPort) {
		return null;
	}
	
	protected  InputPort getPort() {	
		return 
				ReplicatedSessionDuplexRPCPortSelector.
				   createReplicatedSessionGroupRPCServerInputPort(serverHost, serverId, serverName, SESSION_NAME, myId, myName, null);
		
	}
	// this also takes to the session server, so is there a danger it will not send back results to
	// session server.
	// forrtunately, session server does not invoke methods in it that return values
	
	//  this will be  client to the latecomer server
	// the replication is direct not through relayer so we go for the P2p choice
	
	// for clients of this replicated server
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		if (sessionChoice != SessionChoice.P2P)
			return new ASingleResponseReplicatedRelayedGroupServerLauncherSupport(); // has a duplex port to the session server
		else
		return new ASingleResponseReplicatedGroupServerLauncherSupport();

//		if (sessionChoice == SessionChoice.P2P)
//		return new ASingleResponseReplicatedPortLauncherSupport();
//	else
//		return new ARelayedSingleResponseReplicatedPortLauncherSupport();

		
		
	}
	// for the replicated latercomer server
	@Override
	protected PortLauncherSupport getReplicatedAuxilliaryPortLauncherSupport() {
		return new ASingleResponseReplicatedPortLauncherSupport();

		
		
		
	}
	



}
