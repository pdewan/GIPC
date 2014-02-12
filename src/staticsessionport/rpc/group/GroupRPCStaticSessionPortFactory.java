package staticsessionport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.GroupRPCSessionPort;

public interface GroupRPCStaticSessionPortFactory {
	public GroupRPCSessionPort createGroupRPCStaticSessionPort (
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName, 
			String aLogicalRemoteEndPoint,
			ParticipantChoice aChoice);

}
