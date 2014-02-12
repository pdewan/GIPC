package multiserverport.datacomm.duplex.object;


import java.nio.ByteBuffer;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPortFactory;
import multiserverport.datacomm.duplex.buffer.BufferDuplexMultiServerClientPortSelector;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;




public class AnObjectDuplexMultiServerClientPortFactory 
	implements DuplexMultiServerClientPortFactory<Object> {


	@Override
	public DuplexMultiServerClientPort<Object> createDuplexMultiServerClientPort(
			SessionParticipantDescription[] remoteList, String anId, String aName,  ParticipantChoice aChoice) {
		DuplexMultiServerClientPort<ByteBuffer> bbServerPort =  BufferDuplexMultiServerClientPortSelector.createDuplexMultiServerClientPort(remoteList, anId, aName, aChoice);
		return new AnObjectDuplexMultiServerClientPort(bbServerPort);
	}
}
