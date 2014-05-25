package staticsessionport.datacomm.duplex.buffer.fullp2p;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import staticsessionport.datacomm.duplex.DuplexStaticSessionPortFactory;


public class ABufferDuplexStaticSessionPortFullP2PFactory implements DuplexStaticSessionPortFactory<ByteBuffer>{

	@Override
	public DuplexSessionPort<ByteBuffer> createDuplexStaticSessionPort(
			SessionParticipantDescription[] aRemoteList, 
			String anId,
			String aName, 
			String aRemoteEndPoint,
			ParticipantChoice aChoice) {
		return new ABufferDuplexStaticSessionPortFullP2P(aRemoteList, anId, aName, aRemoteEndPoint, aChoice);
	}
}
