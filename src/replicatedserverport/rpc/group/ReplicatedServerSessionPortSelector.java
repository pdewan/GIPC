package replicatedserverport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.GroupSessionPort;

public class ReplicatedServerSessionPortSelector {
	// this seems to be the only factory implementing this interface
	static ReplicatedServerSessionPortFactory factory = new AReplicatedServerSessionPortFactory();
	public static GroupSessionPort createObjectGroupSessionPort(
			SessionParticipantDescription[] aServerDescriptionList, String anId, 
			String aName, String aSessionServerName, String aSessionName, ParticipantChoice aChoice){
		return factory.createObjectGroupSessionPort(aServerDescriptionList, anId, aName, aSessionServerName, aSessionName, aChoice);
	}
	public static void setReplicatedServerSessionPortFactory(ReplicatedServerSessionPortFactory aFactory) {
		factory = aFactory;
	}
	public static  ReplicatedServerSessionPortFactory getReplicatedServerSessionPortFactory() {
		return factory;
	}


}
