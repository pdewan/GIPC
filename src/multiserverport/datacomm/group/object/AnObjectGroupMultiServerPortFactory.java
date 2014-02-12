package multiserverport.datacomm.group.object;

import java.nio.ByteBuffer;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.duplex.object.AnObjectDuplexMultiServerClientPort;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.GroupMultiServerClientPortFactory;
import multiserverport.datacomm.group.buffer.BufferGroupMultiServerClientPortSelector;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;



public class AnObjectGroupMultiServerPortFactory implements GroupMultiServerClientPortFactory<Object> {

	

	@Override
	public GroupMultiServerClientPort<Object> createGroupMultiServerClientPort(
			SessionParticipantDescription[] aServerList, String anId, String aName, 
			ParticipantChoice aChoice) {
		GroupMultiServerClientPort<ByteBuffer> bbPort = 
				BufferGroupMultiServerClientPortSelector.createGroupMultiServerPort(aServerList, anId, aName, aChoice);
		DuplexMultiServerClientPort<Object> duplexPort = new AnObjectDuplexMultiServerClientPort(bbPort);
		return new AnObjectGroupMultiServerClientPort(duplexPort, bbPort);
	}
	
}
