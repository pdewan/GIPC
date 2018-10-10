package sessionport.datacomm.duplex.object.direct;


import java.nio.ByteBuffer;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import sessionport.datacomm.duplex.AnAbstractDuplexSessionPortFactory;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.DuplexSessionPortFactory;
import sessionport.datacomm.duplex.buffer.BufferDuplexSessionPortSelector;



public class ADirectObjectDuplexSessionPortFactory extends AnAbstractDuplexSessionPortFactory<Object>  implements DuplexSessionPortFactory<Object> {


//	@Override
//	public DuplexSessionPort<Object> createDuplexSessionPort(
//			String aSessionsServerHost, String aSessionsServerId,
//			String aSessionsServerName, String aSessionName, String anId,
//			String aName) {
//		DuplexSessionPort<ByteBuffer> bbSessionPort = BufferDuplexSessionPortSelector.createBufferDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName);		
//		return new ALayeredObjectDuplexSessionPort(bbSessionPort);
//	}

//	@Override
//	public DuplexSessionPort<Object> createDuplexSessionPort(
//			SessionsServer aSessionsServer, String aSessionName, String anId,
//			String aName) {
//		DuplexSessionPort<ByteBuffer> bbSessionPort = BufferDuplexSessionPortSelector.createDuplexSessionPort(aSessionsServer, aSessionName, anId, aName);
//		return new ALayeredObjectDuplexSessionPort(bbSessionPort);
//	}
	@Override
	public DuplexSessionPort<Object> createDuplexSessionPort(
			DuplexRPCClientInputPort aSessionsServerClientPort, String aSessionName, String anId,
			String aName, ParticipantChoice aJoinChoice) {
		DuplexSessionPort<ByteBuffer> bbSessionPort = BufferDuplexSessionPortSelector.createDuplexSessionPort(aSessionsServerClientPort, aSessionName, anId, aName, aJoinChoice);
		return new AnObjectDuplexSessionPort(bbSessionPort);
	}
}
