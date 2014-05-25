package replicatedsessionport.datacomm.simplex.object;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;
import port.ParticipantChoice;
import replicatedserverport.datacomm.simplex.AReplicatedServerClientPort;
import replicatedsessionport.datacomm.simplex.ReplicatedSessionPortFactory;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;

public class AReplicatedSessionObjectClientPortFactory 
	implements ReplicatedSessionPortFactory<Object >{

	

	@Override
	public SimplexClientInputPort<Object> createReplicatedSessionClientInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName,
			String aLogicalRemoteEndPoint, String aName
			) {
		GroupSessionPort<Object> sessionPort =
				ObjectGroupSessionPortSelector.
				     createObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, ParticipantChoice.CLIENT_ONLY);
				
			
			// replicated port sends a message to every server
			return new AReplicatedServerClientPort<Object>(sessionPort, aLogicalRemoteEndPoint);	}

	@Override
	public SimplexServerInputPort<Object> createReplicatedSessionServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName) {
		// no simplex port exists curently as it was assumed the joners are symmetric
		DuplexSessionPort<Object> retVal =
			ObjectDuplexSessionPortSelector.
			     createObjectDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY);
		return retVal;
			
		
	}

}
