package staticsessionport.datacomm.group.buffer;


import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.buffer.ABufferGroupSessionPort;
import staticsessionport.datacomm.duplex.buffer.BufferDuplexStaticSessionPortSelector;
import staticsessionport.datacomm.group.GroupStaticSessionPortFactory;



public class ABufferGroupStaticSessionPortFactory implements GroupStaticSessionPortFactory<ByteBuffer> {
	@Override
	public GroupSessionPort<ByteBuffer> createGroupStaticSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName, 
			String aRmoteEndPoint,
			ParticipantChoice aChoice) {
		DuplexSessionPort<ByteBuffer> duplexPort = 
				BufferDuplexStaticSessionPortSelector.createBufferDuplexStaticSessionPort(aServerList, anId, aName, aRmoteEndPoint, aChoice);
		return new ABufferGroupSessionPort (duplexPort);
	}	
}
