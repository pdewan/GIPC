package multiserverport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public class GroupRPCMultiServerClientPortSelector {
	static GroupRPCMultiServerClientPortFactory factory = new AGroupRPCMultiServerClientPortFactory();
	public static void setInputPortFactory(GroupRPCMultiServerClientPortFactory theInputPortFactory) {
		factory = theInputPortFactory;
	}
	public static GroupRPCMultiServerClientPortFactory getInputPortFactory() {
		return factory;
	}	
	public static GroupRPCMultiServerClientPort createGroupRPCMultiServerClientPort 
	(SessionParticipantDescription[] aRemoteList, String anId,	String aName, 
			ParticipantChoice aChoice) {
		return factory.createGroupRPCMultiServerClientPort(aRemoteList, anId, aName, aChoice);
	}
	
}
