package staticsessionport.datacomm.group.object;


import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.object.direct.AnObjectDuplexSessionPort;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.AnObjectGroupSessionPort;
import staticsessionport.datacomm.group.GroupStaticSessionPortFactory;
import staticsessionport.datacomm.group.buffer.BufferGroupStaticSessionPortSelector;






public class AGroupObjectStaticSessionPortFactory implements GroupStaticSessionPortFactory<Object> {

	@Override
	public GroupSessionPort<Object> createGroupStaticSessionPort(
			SessionParticipantDescription[] aServerList, String anId,
			String aName, String aRemoteEndPoint, ParticipantChoice aChoice) {
		GroupSessionPort<ByteBuffer> bbSessionPort = BufferGroupStaticSessionPortSelector.createBufferGroupStaticSessionPort(
				aServerList, anId, aName, aRemoteEndPoint, aChoice);
		DuplexSessionPort<Object> duplexObjectSessionPort = new AnObjectDuplexSessionPort(bbSessionPort);
		return new  AnObjectGroupSessionPort(duplexObjectSessionPort, bbSessionPort);

	}
	
}
