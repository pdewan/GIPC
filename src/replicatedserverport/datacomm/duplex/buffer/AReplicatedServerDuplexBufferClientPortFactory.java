package replicatedserverport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.buffer.BufferGroupMultiServerClientPortSelector;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.duplex.AReplicatedServerDuplexClientPort;
import replicatedserverport.datacomm.duplex.ReplicatedServerDuplexClientPortFactory;

public class AReplicatedServerDuplexBufferClientPortFactory 
	implements ReplicatedServerDuplexClientPortFactory<ByteBuffer >{

	@Override
	public DuplexClientInputPort<ByteBuffer> createDuplexReplicatedServerClientInputPort(
			SessionParticipantDescription[] aServerList, String anId, String aName, ParticipantChoice aChoice) {
		// why not make this static session or a session port?
		// can have a factory that decides
		GroupMultiServerClientPort<ByteBuffer> multiServerPort = 
			BufferGroupMultiServerClientPortSelector.createGroupMultiServerPort(aServerList, anId, aName, aChoice);
		return new AReplicatedServerDuplexClientPort<ByteBuffer>(multiServerPort, anId);
	}

}
