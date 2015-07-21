package replicatedsessionport.rpc.duplex.singleresponse.example;

import inputport.InputPort;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.singleresponse.ARelayedSingleResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.group.flexibleresponse.flexible.ReplicationChoice;
import replicatedserverport.rpc.group.moreflexibleresponse.flexibe.jitter.AJitteryMoreFlexibleResponseReplicatedSessionPortLauncher;
import replicatedsessionport.rpc.duplex.ReplicatedSessionDuplexRPCPortSelector;

public class AJitteryFlexibleReplicatedGroupSessionPortClientLauncher 
	extends AJitteryMoreFlexibleResponseReplicatedSessionPortLauncher{
	String logicalServerName = SERVER_NAME;
	public AJitteryFlexibleReplicatedGroupSessionPortClientLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, 
			String aLogicalServerName, 
//			String aMyId, 
			String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			ReplicationChoice aReplicationChoice,
			boolean aDoCausal,
			SessionParticipantDescription[] aServersDescription, boolean aDoJitter) {
		// so this null argument will signify whether we have a replicated or not session server
		super(aSessionServerHost, aServerId, aServerName, null, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, aReplicationChoice,  ParticipantChoice.CLIENT_ONLY, aServersDescription, aDoJitter);
		if (aLogicalServerName != null)
			logicalServerName = aLogicalServerName;
	}

	
	
	protected  InputPort getPort() {		
		return 
				ReplicatedSessionDuplexRPCPortSelector.
				   createReplicatedSessionRPCClientInputPort(
				       serverHost, serverId, serverName, SESSION_NAME, logicalServerName, myName, serversDescription);
		
	}
	@Override
	protected PortLauncherSupport getSingleResponseReplicatedPortLauncherSupport() {
		if (getSessionChoice() == SessionChoice.P2P)

//		if (sessionChoice == SessionChoice.P2P)
			return new ASingleResponseReplicatedPortLauncherSupport();
		else
			return new ARelayedSingleResponseReplicatedPortLauncherSupport();
	}
	
	// for session server
	@Override
	protected PortLauncherSupport getReplicatedAuxilliaryPortLauncherSupport() {
		return new ASingleResponseReplicatedPortLauncherSupport();
//		if (sessionChoice != SessionChoice.P2P)
//			return new ASingleResponseReplicatedRelayedGroupServerLauncherSupport(); // has a duplex port to the session server
//		else
//		return new ASingleResponseReplicatedGroupServerLauncherSupport();
		
//		if (sessionChoice != SessionChoice.P2P)
//			return new ASingleResponseReplicatedRelayedGroupServerLauncherSupport(); // has a duplex port to the session server
//		else
//		return new ASingleResponseReplicatedGroupServerLauncherSupport();
		
		
		
	}

	
	

}
