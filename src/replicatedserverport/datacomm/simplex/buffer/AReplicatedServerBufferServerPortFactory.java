package replicatedserverport.datacomm.simplex.buffer;

import inputport.datacomm.simplex.SimplexClientInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.buffer.BufferGroupMultiServerClientPortSelector;
import replicatedserverport.datacomm.simplex.AReplicatedServerClientPort;
import replicatedserverport.datacomm.simplex.ReplicatedServerClientPortFactory;

public class AReplicatedServerBufferServerPortFactory 
	implements ReplicatedServerClientPortFactory<ByteBuffer >{

	@Override
	public SimplexClientInputPort<ByteBuffer> createReplicatedServerClientInputPort(
			SessionParticipantDescription[] aServerList, String anId, String aName, ParticipantChoice aChoice) {
		// th emulti server port provides a way to send messages to a group of servers
		// could replace the multi server port with a session port in another factory
		// not sure what the participant choice is going to do here		
		GroupMultiServerClientPort<ByteBuffer> multiServerPort = 
			BufferGroupMultiServerClientPortSelector.createGroupMultiServerPort(aServerList, anId, aName, aChoice);
		// replicated port sends a message to every server
		return new AReplicatedServerClientPort<ByteBuffer>(multiServerPort, anId);
	}

}
