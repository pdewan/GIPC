package replicatedsessionport.datacomm.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import sessionport.datacomm.group.GroupSessionPort;



public interface ReplicatedGroupSessionPortFactory<MessageType> {
	public GroupSessionPort<MessageType> createReplicatedGroupSessionPort(
			String aSessionsServerHost,
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, 
			ParticipantChoice aJoinChoice, 
			SessionParticipantDescription[] aServersList);	
	
//	GroupSessionPort<MessageType> createReplicatedGroupSessionPort(
//			DuplexRPCClientInputPort aSessionsManagerClientPort,
//			String aSessionName, 
//			String anId, 
//			String aName, 
//			ParticipantChoice aChoice, ServerPortDescription[] aServersList);
//	
}
