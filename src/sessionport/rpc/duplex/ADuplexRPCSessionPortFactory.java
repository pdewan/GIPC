package sessionport.rpc.duplex;

import port.ParticipantChoice;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;

public class ADuplexRPCSessionPortFactory implements DuplexRPCSessionPortFactory {	
	public DuplexRPCSessionPort createDuplexRPCSessionPort(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aChoice) {
		DuplexSessionPort<Object> objectDuplexSessionPort = ObjectDuplexSessionPortSelector.createObjectDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aChoice);
		return new ADuplexRPCSessionPort(objectDuplexSessionPort);		
	}
}
