package replicatedsessionport.rpc.duplex;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.sessionserver.SessionParticipantDescription;


public interface ReplicatedSessionDuplexRPCPortFactory {
	public GroupRPCServerInputPort createReplicatedSessionGroupRPCServerInputPort(
			String aSessionsServerHost, String aSessionsServerId,
			String aSessionsServerName, String aSessionName, String anId,
			String aName, SessionParticipantDescription[] aServersDescription) ;
	
	public DuplexRPCServerInputPort createReplicatedSessionDuplexRPCServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, SessionParticipantDescription[] aServersDescription);
	
	

	public DuplexRPCClientInputPort createReplicatedSessionDuplexRPCClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName, SessionParticipantDescription[] aServersDescription);

}
