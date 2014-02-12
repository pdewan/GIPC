package sessionport.datacomm.group;

import port.ParticipantChoice;
import inputport.rpc.duplex.DuplexRPCClientInputPort;



public interface GroupSessionPortFactory<MessageType> {
	public GroupSessionPort<MessageType> createGroupSessionPort(
			String aSessionsServerHost,
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aJoinChoice);	
	
	GroupSessionPort<MessageType> createGroupSessionPort(
			DuplexRPCClientInputPort aSessionsManagerClientPort,
			String aSessionName, 
			String anId, 
			String aName, ParticipantChoice aChoice);
	
}
