package sessionport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.DuplexSessionPortFactory;
import sessionport.datacomm.duplex.buffer.fullp2p.ABufferDuplexSessionPortFullP2PFactory;




public class BufferDuplexSessionPortSelector {
	static DuplexSessionPortFactory<ByteBuffer> factory = new ABufferDuplexSessionPortFullP2PFactory();
	public static void setBufferDuplexSessionPortFactory(DuplexSessionPortFactory<ByteBuffer> aFactory) {
		factory = aFactory;
	}
	public static DuplexSessionPort<ByteBuffer> createBufferDuplexSessionPort(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aJoinChoice) {
		return factory.createDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aJoinChoice);			
	}
	public static DuplexSessionPort<ByteBuffer> createDuplexSessionPort(
			DuplexRPCClientInputPort aSessionsServerClientPort, 
			String aSessionName, 
			String anId, 
			String aName, ParticipantChoice aJoinChoice) {
		return factory.createDuplexSessionPort(aSessionsServerClientPort, aSessionName, anId, aName, aJoinChoice);
	}
}
