package multiserverport.datacomm.group.buffer;


import java.nio.ByteBuffer;

import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.GroupMultiServerClientPortFactory;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;


public class BufferGroupMultiServerClientPortSelector {
	static GroupMultiServerClientPortFactory<ByteBuffer> factory = new ABufferGroupMultiServerPortFactory();
	public static void setBufferGroupInputPortFactory(GroupMultiServerClientPortFactory<ByteBuffer> aFactory) {
		factory = aFactory;
	}	
	public static GroupMultiServerClientPort<ByteBuffer> createGroupMultiServerPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createGroupMultiServerClientPort(aServerList, anId, aName, aChoice);
	}
}
