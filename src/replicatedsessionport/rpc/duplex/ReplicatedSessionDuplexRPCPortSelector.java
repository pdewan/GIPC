package replicatedsessionport.rpc.duplex;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;

public class ReplicatedSessionDuplexRPCPortSelector {
	static ReplicatedSessionDuplexRPCPortFactory factory = new AReplicatedSessionDuplexRPCPortFactory();
	public static void setReplicatedSessionDuplexRPCPortFactory (ReplicatedSessionDuplexRPCPortFactory aFactory) {
		factory  = aFactory;
	}	
	
	public static DuplexRPCServerInputPort createReplicatedSessionRPCServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, SessionParticipantDescription[] aServersDescription) {
		return factory.createReplicatedSessionDuplexRPCServerInputPort
				(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aServersDescription);
	}
	
	public static GroupRPCServerInputPort createReplicatedSessionGroupRPCServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, SessionParticipantDescription[] aServersDescription) {
		return factory.createReplicatedSessionGroupRPCServerInputPort
				(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aServersDescription);
	}

	public static DuplexRPCClientInputPort createReplicatedSessionRPCClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName, SessionParticipantDescription[] aServersDescription) {
		return factory.createReplicatedSessionDuplexRPCClientInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, aServersDescription);
	}

}
