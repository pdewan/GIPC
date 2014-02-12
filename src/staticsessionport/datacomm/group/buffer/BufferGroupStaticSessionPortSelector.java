package staticsessionport.datacomm.group.buffer;


import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.GroupSessionPort;
import staticsessionport.datacomm.group.GroupStaticSessionPortFactory;




public class BufferGroupStaticSessionPortSelector {
	static GroupStaticSessionPortFactory<ByteBuffer> factory = new ABufferGroupStaticSessionPortFactory();
	public static void setBufferGroupInputPortFactory(GroupStaticSessionPortFactory<ByteBuffer> aFactory) {
		factory = aFactory;
	}
	public static GroupSessionPort<ByteBuffer> createBufferGroupStaticSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName, 
			String aRemoteEndPoint,
			ParticipantChoice aChoice) {
		return factory.createGroupStaticSessionPort(aServerList, anId, aName, aRemoteEndPoint, aChoice);			
	}
}
