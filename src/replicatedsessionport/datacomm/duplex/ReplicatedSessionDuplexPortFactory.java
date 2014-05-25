package replicatedsessionport.datacomm.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.group.GroupServerInputPort;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;

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
