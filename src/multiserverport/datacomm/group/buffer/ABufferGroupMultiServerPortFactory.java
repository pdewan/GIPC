package multiserverport.datacomm.group.buffer;


import java.nio.ByteBuffer;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.duplex.buffer.BufferDuplexMultiServerClientPortSelector;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.GroupMultiServerClientPortFactory;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;



public class ABufferGroupMultiServerPortFactory implements GroupMultiServerClientPortFactory<ByteBuffer> {	
	@Override
	public GroupMultiServerClientPort<ByteBuffer> createGroupMultiServerClientPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		DuplexMultiServerClientPort<ByteBuffer> duplexPort = 
				BufferDuplexMultiServerClientPortSelector.createDuplexMultiServerClientPort(aServerList, anId, aName, aChoice);		
		return new ABufferGroupMultiServerClientPort (duplexPort);
	}
}
