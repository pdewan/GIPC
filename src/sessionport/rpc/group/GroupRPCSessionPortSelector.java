package sessionport.rpc.group;

import port.ParticipantChoice;

public class GroupRPCSessionPortSelector {
	static GroupRPCSessionPortFactory factory = new AGroupRPCSessionPortFactory();
	public static void setGroupRPCSessionPortFactory(GroupRPCSessionPortFactory aGroupSessionPortFactory) {
		factory = aGroupSessionPortFactory;
	}
	public static GroupRPCSessionPortFactory getGroupRPCSessionPortFactory() {
		return factory;
	}	
	public static GroupRPCSessionPort createGroupRPCSessionPort (
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice) {
		return factory.createGroupRPCSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aChoice);
	}	
	
}
