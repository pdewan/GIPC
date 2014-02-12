package replicatedserverport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.GroupSessionPort;

public interface ReplicatedServerSessionPortFactory {
	public GroupSessionPort createObjectGroupSessionPort(
			SessionParticipantDescription[] aServerDescriptionList, String anId, String aName, 
			String aSessionServerName, String aSessionName, ParticipantChoice aChoice);

}
