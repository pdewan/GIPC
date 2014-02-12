package replicatedsessionport.datacomm.duplex.object;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import replicatedsessionport.datacomm.duplex.ReplicatedSessionDuplexPortFactory;

public class ReplicatedSessionDuplexObjectPortSelector {
	static ReplicatedSessionDuplexPortFactory<Object>  factory = 
		new AReplicatedSessionDuplexObjectPortFactory();
	
	public static void setDuplexReplicatedSessionObjectClientInputPortFactory (ReplicatedSessionDuplexPortFactory<Object>  newVal) {
		factory = newVal;
	}
	public static DuplexServerInputPort<Object> createDuplexReplicatedSessionServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, SessionParticipantDescription[] aServersDescription) {
		return factory.createDuplexReplicatedSessionServerInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aServersDescription);
		
	}
	
	public static DuplexClientInputPort<Object> createDuplexReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName, SessionParticipantDescription[] aServersDescription) {
		return factory.createDuplexReplicatedSessionClientInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, aServersDescription);
	}
	

}
