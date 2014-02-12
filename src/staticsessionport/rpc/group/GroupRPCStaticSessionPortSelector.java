package staticsessionport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.GroupRPCSessionPort;

public class GroupRPCStaticSessionPortSelector {
	static GroupRPCStaticSessionPortFactory factory = new AGroupRPCStaticSessionPortFactory();
	public static void setGroupRPCSessionPortFactory(GroupRPCStaticSessionPortFactory aGroupSessionPortFactory) {
		factory = aGroupSessionPortFactory;
	}
	public static GroupRPCStaticSessionPortFactory getGroupRPCSessionPortFactory() {
		return factory;
	}	
	public static GroupRPCSessionPort createGroupRPCStaticSessionPort ( 
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName, 
			String aLogicalRemoteEndPoint,
			ParticipantChoice aChoice) {
		return factory.createGroupRPCStaticSessionPort(aServerList, anId, aName, aLogicalRemoteEndPoint, aChoice);
	}	
	
}
