package replicatedsessionport.rpc.duplex.singleresponse.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import inputport.ConnectionListener;
import inputport.InputPort;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseReplicatedGroupServerLauncherSupport;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseReplicatedRelayedGroupServerLauncherSupport;
import replicatedsessionport.rpc.duplex.ReplicatedSessionDuplexRPCPortSelector;
import sessionport.datacomm.group.object.flexible.jitter.example.AJitteryFlexibleSessionPortClientLauncher;

public class AJitteryFlexibleReplicatedGroupSessionPortServerLauncher 
	extends AJitteryFlexibleSessionPortClientLauncher{ // now this should inherit from the replicated choice

	public AJitteryFlexibleReplicatedGroupSessionPortServerLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal,
			boolean aDoJitter) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, ParticipantChoice.SERVER_ONLY, aDoJitter);
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
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		if (sessionChoice != SessionChoice.P2P)
			return new ASingleResponseReplicatedRelayedGroupServerLauncherSupport(); // has a duplex port to the session server
		else
		return new ASingleResponseReplicatedGroupServerLauncherSupport();
	}


}
