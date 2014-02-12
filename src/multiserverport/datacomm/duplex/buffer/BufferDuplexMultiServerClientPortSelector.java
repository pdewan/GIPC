package multiserverport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPortFactory;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;


public class BufferDuplexMultiServerClientPortSelector {
	static DuplexMultiServerClientPortFactory<ByteBuffer> factory = new ABufferDuplexMultiServerClientPortFactory();
	public static void setBufferDuplexMultiServerPortFactory(DuplexMultiServerClientPortFactory<ByteBuffer>  aFactory) {
		factory = aFactory;
	}	

	public static DuplexMultiServerClientPort<ByteBuffer> createDuplexMultiServerClientPort(
			SessionParticipantDescription[] aRemoteList, 
			String anId,
			String aName, ParticipantChoice aChoice) {
		return factory.createDuplexMultiServerClientPort(aRemoteList, anId, aName, aChoice);

	}
}
