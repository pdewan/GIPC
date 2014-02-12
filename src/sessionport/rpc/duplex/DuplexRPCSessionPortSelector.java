package sessionport.rpc.duplex;

import port.ParticipantChoice;

public class DuplexRPCSessionPortSelector {
	static DuplexRPCSessionPortFactory factory = new ADuplexRPCSessionPortFactory();
	public static void setSessionPortFactory(DuplexRPCSessionPortFactory theSessionPortFactory) {
		factory = theSessionPortFactory;
	}
	public static DuplexRPCSessionPortFactory getSessionPortFactory() {
		return factory;
	}	
	public static DuplexRPCSessionPort createDuplexRPCSessionPort(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice) {
//		return factory.createDuplexRPCSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, ParticipantChoice.MEMBER);			

		return factory.createDuplexRPCSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aChoice);			
	}
}
