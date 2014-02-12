package replicatedsessionport.datacomm.simplex.buffer;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;

import replicatedserverport.datacomm.simplex.AReplicatedServerClientPort;
import replicatedsessionport.datacomm.simplex.ReplicatedSessionPortFactory;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.buffer.BufferDuplexSessionPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.buffer.BufferGroupSessionPortSelector;

public class AReplicatedSessionBufferPortFactory 
	implements ReplicatedSessionPortFactory<ByteBuffer >{
	public SimplexServerInputPort<ByteBuffer> createReplicatedSessionServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName) {
		// th emulti server port provides a way to send messages to a group of servers
		// could replace the multi server port with a session port in another factory
		// not sure what the participant choice is going to do here	
		// creating session port instead of multiserver port
		
		// no simplex port exists curently as it was assumed the joners are symmetric
		DuplexSessionPort<ByteBuffer> sessionPort =
			BufferDuplexSessionPortSelector.
			     createBufferDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY);
			
		
		// replicated port sends a message to every server
		return sessionPort;
	}

	
	public SimplexClientInputPort<ByteBuffer> createReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName) {
		// th emulti server port provides a way to send messages to a group of servers
		// could replace the multi server port with a session port in another factory
		// not sure what the participant choice is going to do here	
		// creating session port instead of multiserver port
		
		// no id, client does not register
		GroupSessionPort<ByteBuffer> sessionPort =
			BufferGroupSessionPortSelector.
			     createBufferGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, null, aName, ParticipantChoice.CLIENT_ONLY);
			
		
		// replicated port sends a message to every server
		return new AReplicatedServerClientPort<ByteBuffer>(sessionPort, aLogicalRemoteEndPoint);
	}

}
