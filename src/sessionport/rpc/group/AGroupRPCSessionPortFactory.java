package sessionport.rpc.group;

import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;

public class AGroupRPCSessionPortFactory implements GroupRPCSessionPortFactory {
	// so this one could take extra arguments to support replicated session managers?
	@Override
	public GroupRPCSessionPort createGroupRPCSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aChoice) {
		GroupSessionPort<Object> objectSessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aChoice);
		return new AGroupRPCSessionPort(objectSessionPort); 
	}

	

}
