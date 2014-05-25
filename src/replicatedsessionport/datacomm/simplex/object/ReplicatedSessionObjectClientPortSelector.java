package replicatedsessionport.datacomm.simplex.object;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;
import port.ParticipantChoice;
import replicatedsessionport.datacomm.simplex.ReplicatedSessionPortFactory;

public class ReplicatedSessionObjectClientPortSelector {
	static ReplicatedSessionPortFactory<Object>  factory = 
		new AReplicatedSessionObjectClientPortFactory();
	
	public static void setReplicatedSessionObjectClientInputPortFactory (ReplicatedSessionPortFactory<Object>  newVal) {
		factory = newVal;
	}
	
	
	
	public static SimplexClientInputPort<Object> createReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName, ParticipantChoice aJoinChoice) {
		return factory.createReplicatedSessionClientInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName);
	}
	public SimplexServerInputPort createReplicatedSessionServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName) {
		return factory.createReplicatedSessionServerInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName);
		
	}

}
