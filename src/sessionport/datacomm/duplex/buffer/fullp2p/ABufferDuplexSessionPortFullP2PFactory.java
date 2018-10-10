package sessionport.datacomm.duplex.buffer.fullp2p;

import java.nio.ByteBuffer;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import sessionport.datacomm.duplex.AnAbstractDuplexSessionPortFactory;
import sessionport.datacomm.duplex.DuplexSessionPort;


public class ABufferDuplexSessionPortFullP2PFactory extends  AnAbstractDuplexSessionPortFactory<ByteBuffer>{

//	@Override
//	public DuplexSessionPort<ByteBuffer> createDuplexSessionPort(
//			String aSessionsServerHost, String aSessionsServerId,
//			String aSessionsServerName, String aSessionName, String anId,
//			String aName) {
//		DuplexRPCClientInputPort sessionsServerInputPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(aSessionsServerHost, 
//				aSessionsServerId, aSessionsServerName, aName);		
//		sessionsServerInputPort.connect();
//		try {
//			SessionsServer sessionServerProxy = (SessionsServer) UniRPCProxyGenerator.generateUniRPCProxy(sessionsServerInputPort, aSessionsServerName,
//					SessionsServer.class, aSessionsServerName);
//		return createDuplexSessionPort(sessionServerProxy, aSessionName, anId, aName);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	@Override
//	public DuplexSessionPort<ByteBuffer> createDuplexSessionPort(SessionsServer aSessionsServer, String aSessionName, String anId, String aName) {
//		return new ABufferDuplexSessionPortFullP2P(aSessionsServer, aSessionName, anId, aName);
//	}
	
	@Override
	public DuplexSessionPort<ByteBuffer> createDuplexSessionPort(DuplexRPCClientInputPort aSessionServerClientPort, String aSessionName, String anId, String aName, ParticipantChoice aJonChoice) {
		return new ABufferDuplexSessionPortFullP2P(aSessionServerClientPort, aSessionName, anId, aName, aJonChoice);
	}
}
