package replicatedsessionport.rpc.simplex;

import port.ParticipantChoice;
import port.sessionserver.ServerPortDescription;
import inputport.rpc.simplex.SimplexRPCClientInputPort;

public interface ReplicatedSessionRPCClientPortFactory {
	public SimplexRPCClientInputPort createRPCLientPort(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName);

}
