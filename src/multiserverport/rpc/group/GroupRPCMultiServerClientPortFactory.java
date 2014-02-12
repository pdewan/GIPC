package multiserverport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public interface GroupRPCMultiServerClientPortFactory {
	public GroupRPCMultiServerClientPort createGroupRPCMultiServerClientPort 
		(SessionParticipantDescription[] aRemoteList, String anId,	String aName, 
				ParticipantChoice aChoice);

}
