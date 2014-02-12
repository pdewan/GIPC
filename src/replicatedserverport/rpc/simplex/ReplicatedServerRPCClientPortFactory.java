package replicatedserverport.rpc.simplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.rpc.simplex.SimplexRPCClientInputPort;

public interface ReplicatedServerRPCClientPortFactory {
	public SimplexRPCClientInputPort createRPCPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice);

}
