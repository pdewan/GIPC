package sessionport.datacomm.group.object.direct;


import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.object.direct.AnObjectDuplexSessionPort;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.GroupSessionPortFactory;
import sessionport.datacomm.group.buffer.BufferGroupSessionPortSelector;
import sessionport.datacomm.group.object.AnObjectGroupSessionPort;






public class ALayeredObjectGroupSessionPortFactory implements GroupSessionPortFactory<Object> {

	@Override
	public GroupSessionPort<Object> createGroupSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aJoinChoice) {
		GroupSessionPort<ByteBuffer> bbSessionPort = BufferGroupSessionPortSelector.createBufferGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aJoinChoice);
		return createGroupSessionPort(bbSessionPort);
	}

	@Override
	public GroupSessionPort<Object> createGroupSessionPort(
			DuplexRPCClientInputPort aSessionsManagerClienPort,
			String aSessionName, String anId, String aName, ParticipantChoice aChoice) {
		GroupSessionPort<ByteBuffer> bbSessionPort = BufferGroupSessionPortSelector.createGroupSessionPort(aSessionsManagerClienPort, aSessionName, anId, aName, aChoice);
		return createGroupSessionPort(bbSessionPort);
//		DuplexSessionPort<Object> duplexObjectSessionPort = new ALayeredObjectDuplexSessionPort(bbSessionPort);
//		return  new AnObjectGroupSessionPort(duplexObjectSessionPort, bbSessionPort);
	}
	
	 GroupSessionPort<Object> createGroupSessionPort(GroupSessionPort<ByteBuffer> bbSessionPort) {
		 DuplexSessionPort<Object> duplexObjectSessionPort = new AnObjectDuplexSessionPort(bbSessionPort);
		 return new  AnObjectGroupSessionPort(duplexObjectSessionPort, bbSessionPort);
	 }
	
}
