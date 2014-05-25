package replicatedserverport.rpc.simplex;

import inputport.rpc.simplex.SimplexRPCClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public interface ReplicatedServerRPCClientPortFactory {
	public SimplexRPCClientInputPort createRPCPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice);

}
