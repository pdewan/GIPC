package replicatedsessionport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedsessionport.datacomm.group.object.ReplicatedObjectGroupSessionPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.rpc.group.AGroupRPCSessionPort;
import sessionport.rpc.group.GroupRPCSessionPort;

public class AReplicatedGroupRPCSessionPortFactory implements ReplicatedGroupRPCSessionPortFactory {
	// so this one could take extra arguments to support replicated session managers?
	@Override
	public GroupRPCSessionPort createGroupRPCSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aChoice, SessionParticipantDescription[] aServerList) {
		GroupSessionPort<Object> objectSessionPort = ReplicatedObjectGroupSessionPortSelector.createReplicatedObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, 
				aSessionName, anId, aName, aChoice, aServerList);
		return new AGroupRPCSessionPort(objectSessionPort); 
	}

	

}
