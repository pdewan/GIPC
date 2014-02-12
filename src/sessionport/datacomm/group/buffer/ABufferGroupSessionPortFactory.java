package sessionport.datacomm.group.buffer;


import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;

import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.buffer.BufferDuplexSessionPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.GroupSessionPortFactory;



public class ABufferGroupSessionPortFactory implements GroupSessionPortFactory<ByteBuffer> {
	@Override
	public GroupSessionPort<ByteBuffer> createGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aJoinChoice) {
		DuplexSessionPort<ByteBuffer> duplexPort = BufferDuplexSessionPortSelector.createBufferDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aJoinChoice);
		return new ABufferGroupSessionPort (duplexPort);
	}

	@Override
	public GroupSessionPort<ByteBuffer> createGroupSessionPort(
			DuplexRPCClientInputPort aSessionsServerClientPort,
			String aSessionName, 
			String anId, 
			String aName, ParticipantChoice aChoice) {
		DuplexSessionPort<ByteBuffer> duplexPort = BufferDuplexSessionPortSelector.createDuplexSessionPort(
				aSessionsServerClientPort, 
				aSessionName, anId, 
				aName, ParticipantChoice.MEMBER);
		return new ABufferGroupSessionPort (duplexPort);
	}	
}
