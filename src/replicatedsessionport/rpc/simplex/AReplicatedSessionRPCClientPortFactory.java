package replicatedsessionport.rpc.simplex;

import port.ParticipantChoice;
import port.sessionserver.ServerPortDescription;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.rpc.simplex.SimplexRPCClientInputPort;
import replicatedserverport.rpc.simplex.AReplicatedServerRPCClientPort;
import replicatedsessionport.datacomm.simplex.object.ReplicatedSessionObjectClientPortSelector;

public class AReplicatedSessionRPCClientPortFactory implements ReplicatedSessionRPCClientPortFactory {	
	
	// simplex makes not much sense
	
	@Override
	public SimplexRPCClientInputPort createRPCLientPort(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName){
			SimplexClientInputPort<Object> port = 
					ReplicatedSessionObjectClientPortSelector.
					     createReplicatedSessionClientInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, ParticipantChoice.CLIENT_ONLY);
//			ReplicatedServerDuplexObjectClientPortSelector.createDuplexReplicatedServerClientInputPort(aServerList, anId, aName);
			return new AReplicatedServerRPCClientPort(port);
	}

	
}
