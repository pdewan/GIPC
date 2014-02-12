package multiserverport.datacomm.simplex;

import port.sessionserver.SessionParticipantDescription;

public interface SimplexMultiServerClientPortFactory<MessageType> {
	SimplexMultiServerClientPort<MessageType> createMultiServerClientPort(
			SessionParticipantDescription[] aRemoteList, 
										String anId,
										String aName);	
	

}
