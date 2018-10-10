package replicatedsessionport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.group.GroupServerInputPort;
import port.ParticipantChoice;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.duplex.AReplicatedServerDuplexClientPort;
import replicatedsessionport.datacomm.duplex.ReplicatedSessionDuplexPortFactory;
import sessionport.datacomm.duplex.buffer.BufferDuplexSessionPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.buffer.BufferGroupSessionPortSelector;
// the duplex has to do with the client and not the server
public class AReplicatedSessionDuplexBufferPortFactory 
	implements ReplicatedSessionDuplexPortFactory<ByteBuffer >{
	
	@Override
	public DuplexServerInputPort<ByteBuffer> createDuplexReplicatedSessionServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, SessionParticipantDescription[] aServersDescription) {
		// TODO Auto-generated method stub
		return 
				BufferDuplexSessionPortSelector.
			     createBufferDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY);
			
	}
	@Override
	public GroupServerInputPort<ByteBuffer> createGroupReplicatedSessionServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ServerPortDescription[] aServersDescription) {
		// TODO Auto-generated method stub
		return 
				BufferGroupSessionPortSelector.
			     createBufferGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.SERVER_ONLY);
			
	}

	public DuplexClientInputPort<ByteBuffer> createDuplexReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName, SessionParticipantDescription[] aServersDescription) {
		
		
		GroupSessionPort<ByteBuffer> sessionPort =
			BufferGroupSessionPortSelector.
			     createBufferGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, ParticipantChoice.CLIENT_ONLY);
			
		
		// replicated port sends a message to every server
		return new AReplicatedServerDuplexClientPort<ByteBuffer>(sessionPort, aLogicalRemoteEndPoint);
	}

	
	
}
