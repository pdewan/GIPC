package staticsessionport.datacomm.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.GroupSessionPort;



public interface GroupStaticSessionPortFactory<MessageType> {
	public GroupSessionPort<MessageType> createGroupStaticSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName, 
			String aRmoteEndPoint,
			ParticipantChoice aChoice);	
	
}
