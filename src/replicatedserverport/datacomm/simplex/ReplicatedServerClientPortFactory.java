package replicatedserverport.datacomm.simplex;

import inputport.datacomm.simplex.SimplexClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public interface ReplicatedServerClientPortFactory<MessageType> {
	SimplexClientInputPort<MessageType> createReplicatedServerClientInputPort(
										SessionParticipantDescription[] aRemoteList, 
										String anId,
										String aName,
										ParticipantChoice aChoice);	
	

}
