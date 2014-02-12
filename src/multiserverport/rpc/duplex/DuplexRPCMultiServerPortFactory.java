package multiserverport.rpc.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public interface DuplexRPCMultiServerPortFactory {
	public DuplexRPCMultiServerClientPort createDuplexRPCMultiServerClientPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice);

}
