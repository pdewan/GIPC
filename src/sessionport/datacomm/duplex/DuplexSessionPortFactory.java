package sessionport.datacomm.duplex;

import port.ParticipantChoice;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

public interface DuplexSessionPortFactory<MessageType> {
	DuplexSessionPort<MessageType> createDuplexSessionPort(
										String aSessionsServerHost, 
										String aSessionsServerId, 
										String aSessionsServerName, 
										String aSessionName, 
										String anId,
										String aName, ParticipantChoice aJoinChoice);	
	DuplexSessionPort<MessageType> createDuplexSessionPort(
//										SessionsServer aSessionsServer, 
			
										DuplexRPCClientInputPort aSessionsManagerClienPort,
										String aSessionName, 
										String anId, 
										String aName, ParticipantChoice aJonChoice);
}
