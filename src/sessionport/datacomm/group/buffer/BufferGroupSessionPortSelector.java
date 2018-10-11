package sessionport.datacomm.group.buffer;


import java.nio.ByteBuffer;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.GroupSessionPortFactory;




public class BufferGroupSessionPortSelector {
	static GroupSessionPortFactory<ByteBuffer> factory = new ABufferGroupSessionPortFactory();
	public static void setBufferGroupInputPortFactory(GroupSessionPortFactory<ByteBuffer> aFactory) {
		factory = aFactory;
	}
	public static GroupSessionPort<ByteBuffer> createBufferGroupSessionPort(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aJoinChoice) {
		return factory.createGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aJoinChoice);			
	}
	
	public static GroupSessionPort<ByteBuffer> createGroupSessionPort(
			DuplexRPCClientInputPort aSessionsManagerClienPort,
			String aSessionName, 
			String anId, 
			String aName, ParticipantChoice aJoinChoice) {
		return factory.createGroupSessionPort(aSessionsManagerClienPort, aSessionName, anId, aName, aJoinChoice);
	}
}
