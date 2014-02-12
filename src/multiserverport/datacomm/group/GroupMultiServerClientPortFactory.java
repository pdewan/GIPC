package multiserverport.datacomm.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;



public interface GroupMultiServerClientPortFactory<MessageType> {
	public GroupMultiServerClientPort<MessageType> createGroupMultiServerClientPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice);	
	
}
