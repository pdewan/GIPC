package replicatedserverport.rpc.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

public interface ReplicatedServerDuplexRPCClientPortFactory {
	public DuplexRPCClientInputPort createDuplexRPCPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice);

}
