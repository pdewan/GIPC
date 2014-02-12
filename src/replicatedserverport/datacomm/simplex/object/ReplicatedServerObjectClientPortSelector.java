package replicatedserverport.datacomm.simplex.object;

import inputport.datacomm.simplex.SimplexClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.simplex.ReplicatedServerClientPortFactory;

public class ReplicatedServerObjectClientPortSelector {
	static ReplicatedServerClientPortFactory<Object>  factory = 
		new AReplicatedServerObjectClientPortFactory();
	
	public static void setReplicatedServerObjectClientInputPortFactory (ReplicatedServerClientPortFactory<Object>  newVal) {
		factory = newVal;
	}
	
	public static SimplexClientInputPort<Object> createReplicatedServerClientInputPort(
			SessionParticipantDescription[] aRemoteList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createReplicatedServerClientInputPort(aRemoteList, anId, aName, aChoice);
	}
	

}
