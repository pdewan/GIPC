package multiserverport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import multiserverport.datacomm.simplex.SimplexMultiServerClientPort;
import multiserverport.datacomm.simplex.SimplexMultiServerClientPortFactory;
import port.sessionserver.SessionParticipantDescription;



public class ABufferMultiServerClientPortFactory implements SimplexMultiServerClientPortFactory<ByteBuffer>{


	@Override
	public SimplexMultiServerClientPort<ByteBuffer> createMultiServerClientPort(
			SessionParticipantDescription[] aRemoteList, String anId, String aName) {
		return new ABufferSimplexMultiServerClientPort(aRemoteList, anId, aName);
	}
}
