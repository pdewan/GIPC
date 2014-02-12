package replicatedsessionport.datacomm.duplex;

import java.nio.ByteBuffer;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;


import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.group.GroupServerInputPort;

public interface ReplicatedSessionDuplexPortFactory<MessageType> {
	
	public DuplexServerInputPort<MessageType> createDuplexReplicatedSessionServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, SessionParticipantDescription[] aServersDescription);	

	public DuplexClientInputPort<MessageType> createDuplexReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName, SessionParticipantDescription[] aServersDescription);

	GroupServerInputPort<MessageType> createGroupReplicatedSessionServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ServerPortDescription[] aServersDescription);
	
	

}
