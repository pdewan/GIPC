package sessionport.datacomm.duplex;

import port.ParticipantChoice;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;


public abstract class AnAbstractDuplexSessionPortFactory<MessageType> implements DuplexSessionPortFactory<MessageType>{

	@Override
	public DuplexSessionPort<MessageType> createDuplexSessionPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, ParticipantChoice aJoinChoice) {
		DuplexRPCClientInputPort aSessionsServerInputPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(aSessionsServerHost, 
				aSessionsServerId, aSessionsServerName, aName);		
//		aSessionsServerInputPort.connect();
		return createDuplexSessionPort(aSessionsServerInputPort, aSessionName, anId, aName, aJoinChoice);

//		try {
//			SessionsServer sessionServerProxy = (SessionsServer) UniRPCProxyGenerator.generateUniRPCProxy(sessionsServerInputPort, aSessionsServerName,
//					sessionsServerClass(), aSessionsServerName);
//		return createDuplexSessionPort(sessionServerProxy, aSessionName, anId, aName);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}
	
	
//	protected Class sessionsServerClass() {
//		return SessionsServer.class;
//	}
	
}
