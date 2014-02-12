package replicatedsessionport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.GroupRPCSessionPort;

public class ReplicatedGroupRPCSessionPortSelector {
	static ReplicatedGroupRPCSessionPortFactory factory = new AReplicatedGroupRPCSessionPortFactory();
	public static void setGroupRPCSessionPortFactory(ReplicatedGroupRPCSessionPortFactory aGroupSessionPortFactory) {
		factory = aGroupSessionPortFactory;
	}
	public static ReplicatedGroupRPCSessionPortFactory getGroupRPCSessionPortFactory() {
		return factory;
	}	
	public static GroupRPCSessionPort createReplicatedGroupRPCSessionPort (
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice, SessionParticipantDescription[] aServersList) {
		return factory.createGroupRPCSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aChoice, aServersList);
	}	
	
}
