package staticsessionport.rpc.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.duplex.DuplexRPCSessionPort;

public interface DuplexRPCStaticSessionPortFactory {
	public DuplexRPCSessionPort createDuplexRPCSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice);

}
