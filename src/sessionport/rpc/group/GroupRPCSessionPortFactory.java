package sessionport.rpc.group;

import port.ParticipantChoice;

public interface GroupRPCSessionPortFactory {
	public GroupRPCSessionPort createGroupRPCSessionPort (
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice);

}
