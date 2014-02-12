package replicatedsessionport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.GroupRPCSessionPort;

public interface ReplicatedGroupRPCSessionPortFactory {
	
	public GroupRPCSessionPort createGroupRPCSessionPort (
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice, SessionParticipantDescription[] aServerList);

}
