package sessionport.rpc.duplex;

import port.ParticipantChoice;

public interface DuplexRPCSessionPortFactory {
	public DuplexRPCSessionPort createDuplexRPCSessionPort(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice);

}
