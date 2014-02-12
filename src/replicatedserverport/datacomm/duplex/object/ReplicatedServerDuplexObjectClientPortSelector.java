package replicatedserverport.datacomm.duplex.object;

import inputport.datacomm.duplex.DuplexClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.duplex.ReplicatedServerDuplexClientPortFactory;

public class ReplicatedServerDuplexObjectClientPortSelector {
	static ReplicatedServerDuplexClientPortFactory<Object>  factory = 
		new AReplicatedServerDuplexObjectClientPortFactory();
	
	public static void setDuplexReplicatedServerObjectClientInputPortFactory (ReplicatedServerDuplexClientPortFactory<Object>  newVal) {
		factory = newVal;
	}
	
	public static DuplexClientInputPort<Object> createDuplexReplicatedServerClientInputPort(
			SessionParticipantDescription[] aRemoteList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createDuplexReplicatedServerClientInputPort(aRemoteList, anId, aName, aChoice);
	}
	

}
