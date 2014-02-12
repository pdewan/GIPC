package replicatedserverport.datacomm.duplex.buffer;

import inputport.datacomm.duplex.DuplexClientInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.duplex.ReplicatedServerDuplexClientPortFactory;

public class ReplicatedServerDuplexBufferClientPortSelector {
	static ReplicatedServerDuplexClientPortFactory<ByteBuffer>  factory = 
		new AReplicatedServerDuplexBufferClientPortFactory();
	
	public static void setDuplexReplicatedServerObjectClientInputPortFactory (ReplicatedServerDuplexClientPortFactory<ByteBuffer>  newVal) {
		factory = newVal;
	}
	
	public static DuplexClientInputPort<ByteBuffer> createDuplexReplicatedServerClientInputPort(
			SessionParticipantDescription[] aRemoteList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createDuplexReplicatedServerClientInputPort(aRemoteList, anId, aName, aChoice);
	}
	

}
