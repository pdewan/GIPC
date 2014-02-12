package replicatedserverport.datacomm.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public interface ReplicatedServerDuplexClientPortFactory<MessageType> {
	DuplexClientInputPort<MessageType> createDuplexReplicatedServerClientInputPort(
			SessionParticipantDescription[] aRemoteList, 
										String anId,
										String aName,
										ParticipantChoice aChoice);	
	

}
