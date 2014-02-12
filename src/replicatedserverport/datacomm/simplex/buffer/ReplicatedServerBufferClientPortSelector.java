package replicatedserverport.datacomm.simplex.buffer;

import inputport.datacomm.simplex.SimplexClientInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.simplex.ReplicatedServerClientPortFactory;

public class ReplicatedServerBufferClientPortSelector {
	static ReplicatedServerClientPortFactory<ByteBuffer>  factory = 
		new AReplicatedServerBufferClientPortFactory();
	
	public static void setReplicatedServerObjectClientInputPortFactory (ReplicatedServerClientPortFactory<ByteBuffer>  newVal) {
		factory = newVal;
	}
	
	public static SimplexClientInputPort<ByteBuffer> createReplicatedServerClientInputPort(
			SessionParticipantDescription[] aRemoteList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createReplicatedServerClientInputPort(aRemoteList, anId, aName, aChoice);
	}
	

}
