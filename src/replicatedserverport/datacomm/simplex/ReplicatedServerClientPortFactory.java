package replicatedserverport.datacomm.simplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.datacomm.simplex.SimplexClientInputPort;

public interface ReplicatedServerClientPortFactory<MessageType> {
	SimplexClientInputPort<MessageType> createReplicatedServerClientInputPort(
										SessionParticipantDescription[] aRemoteList, 
										String anId,
										String aName,
										ParticipantChoice aChoice);	
	

}
