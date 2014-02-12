package staticsessionport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import staticsessionport.datacomm.duplex.DuplexStaticSessionPortFactory;
import staticsessionport.datacomm.duplex.buffer.fullp2p.ABufferDuplexStaticSessionPortFullP2PFactory;




public class BufferDuplexStaticSessionPortSelector {
	static DuplexStaticSessionPortFactory<ByteBuffer> factory = new ABufferDuplexStaticSessionPortFullP2PFactory();
	public static void setBufferDuplexStaticSessionPortFactory(DuplexStaticSessionPortFactory<ByteBuffer> aFactory) {
		factory = aFactory;
	}
	public static DuplexSessionPort<ByteBuffer> createBufferDuplexStaticSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName, 
			String aRemoteEndPoint,
			ParticipantChoice aChoice) {
		return factory.createDuplexStaticSessionPort(aServerList, anId, aName, aRemoteEndPoint, aChoice);			
	}
}
