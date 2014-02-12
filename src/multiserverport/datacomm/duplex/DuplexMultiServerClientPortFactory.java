package multiserverport.datacomm.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public interface DuplexMultiServerClientPortFactory<MessageType> {
	DuplexMultiServerClientPort<MessageType> createDuplexMultiServerClientPort(
			SessionParticipantDescription[] aRemoteList, 
										String anId,
										String aName,
										ParticipantChoice aChoice);	
	

}
