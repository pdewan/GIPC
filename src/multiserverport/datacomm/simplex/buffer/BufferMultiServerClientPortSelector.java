package multiserverport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import multiserverport.datacomm.simplex.SimplexMultiServerClientPort;
import multiserverport.datacomm.simplex.SimplexMultiServerClientPortFactory;
import port.sessionserver.SessionParticipantDescription;


public class BufferMultiServerClientPortSelector {
	static SimplexMultiServerClientPortFactory<ByteBuffer> factory = new ABufferMultiServerClientPortFactory();
	public static void setBufferDuplexMultiServerPortFactory(SimplexMultiServerClientPortFactory<ByteBuffer>  aFactory) {
		factory = aFactory;
	}	

	public static SimplexMultiServerClientPort<ByteBuffer> createMultiServerClientPort(
			SessionParticipantDescription[] aRemoteList, 
			String anId,
			String aName) {
		return factory.createMultiServerClientPort(aRemoteList, anId, aName);

	}
}
