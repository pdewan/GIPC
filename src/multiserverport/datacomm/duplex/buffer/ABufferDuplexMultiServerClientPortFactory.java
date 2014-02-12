package multiserverport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPortFactory;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;



public class ABufferDuplexMultiServerClientPortFactory implements DuplexMultiServerClientPortFactory<ByteBuffer>{

//	@Override
//	public DuplexSessionPort<ByteBuffer> createDuplexStaticSessionPort(
//			String anId,
//			String aName) {
//		return new ABufferDuplexStaticSessionPortFullP2P(aRemoteList, anId, aName);
//	}
	// id is really a name
	@Override
	public DuplexMultiServerClientPort<ByteBuffer> createDuplexMultiServerClientPort(
			SessionParticipantDescription[] aRemoteList, String anId, String aName, ParticipantChoice aChoice) {
		return new ABufferDuplexMultiServerClientPort(aRemoteList, anId, aName, aChoice);
	}
}
