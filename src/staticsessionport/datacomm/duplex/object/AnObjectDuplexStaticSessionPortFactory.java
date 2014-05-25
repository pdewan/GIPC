package staticsessionport.datacomm.duplex.object;


import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.object.direct.AnObjectDuplexSessionPort;
import staticsessionport.datacomm.duplex.DuplexStaticSessionPortFactory;
import staticsessionport.datacomm.duplex.buffer.BufferDuplexStaticSessionPortSelector;



public class AnObjectDuplexStaticSessionPortFactory implements DuplexStaticSessionPortFactory<Object> {


	@Override
	public DuplexSessionPort<Object> createDuplexStaticSessionPort(
			SessionParticipantDescription[] aServerList, String anId,
			String aName, String aRemoteEndPoint, ParticipantChoice aChoice) {
		DuplexSessionPort<ByteBuffer> bbSessionPort = 
				BufferDuplexStaticSessionPortSelector.createBufferDuplexStaticSessionPort(aServerList, anId, aName, null, aChoice);		
		return new AnObjectDuplexSessionPort(bbSessionPort);
	}
}
