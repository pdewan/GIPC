package replicatedsessionport.datacomm.duplex.object;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.group.GroupServerInputPort;
import port.ParticipantChoice;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.duplex.AReplicatedServerDuplexClientPort;
import replicatedsessionport.datacomm.duplex.ReplicatedSessionDuplexPortFactory;
import replicatedsessionport.datacomm.group.object.ReplicatedObjectGroupSessionPortSelector;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;

public class AReplicatedSessionDuplexObjectPortFactory 
	implements ReplicatedSessionDuplexPortFactory<Object >{
	
	@Override
	public DuplexServerInputPort<Object> createDuplexReplicatedSessionServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, SessionParticipantDescription[] aServersDescription) {
		return 
				ObjectDuplexSessionPortSelector.
			     createObjectDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY);
			
	}
	

	@Override
	public GroupServerInputPort<Object> createGroupReplicatedSessionServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ServerPortDescription[] aServersDescription) {
		return 
				ObjectGroupSessionPortSelector.
			     createObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY);
	}
	// depending on whether server list is null or not, we either use ObjectGroupSessionPortSelector or ReplicatedSelector
	// as in 	DuplexRPCClientInputPort sessionManagerClientPort = 
//;ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(aServersDescription, 
////		aSessionsServerName, 
////		aName,
////		ParticipantChoice.CLIENT_ONLY)
	
	public DuplexClientInputPort<Object> createDuplexReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName, SessionParticipantDescription[] aServersDescription) {
		GroupSessionPort<Object> sessionPort = null;
		if (aServersDescription == null)
			sessionPort = ObjectGroupSessionPortSelector.
				     createObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, ParticipantChoice.CLIENT_ONLY);
		else
			sessionPort = ReplicatedObjectGroupSessionPortSelector.
		     createReplicatedObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, 
		    		 ParticipantChoice.CLIENT_ONLY, aServersDescription);
//		else
//			sessionPort = ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(aServersDescription, 
//					aSessionsServerName, 
//					aName,
//					ParticipantChoice.CLIENT_ONLY)
//		
//		GroupSessionPort<Object> sessionPort =
//			ObjectGroupSessionPortSelector.
//			     createObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, ParticipantChoice.CLIENT_ONLY);
//			
		
		// replicated port sends a message to every server
		return new AReplicatedServerDuplexClientPort<Object>(sessionPort, aLogicalRemoteEndPoint);
	}


	
	
}
