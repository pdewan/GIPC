package replicatedserverport.rpc.duplex;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public interface ReplicatedServerDuplexRPCClientPortFactory {
	public DuplexRPCClientInputPort createDuplexRPCPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice);

}
