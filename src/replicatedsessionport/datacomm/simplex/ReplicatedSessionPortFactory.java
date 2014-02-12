package replicatedsessionport.datacomm.simplex;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.ServerPortDescription;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;

public interface ReplicatedSessionPortFactory<MessageType> {
	public SimplexClientInputPort<MessageType> createReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName);
	public SimplexServerInputPort<MessageType> createReplicatedSessionServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName);	

}
