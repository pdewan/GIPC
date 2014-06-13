package replicatedserverport.rpc.duplex.singleresponse.example;

import port.ParticipantChoice;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;

public interface GroupRPCServer1Launcher {
//	public static final SessionParticipantDescription SERVER_1_DESCRIPTION = new ASessionParticipantDescription("localhost", "9090", "Server1", ParticipantChoice.SERVER_ONLY, null);
	public static final SessionParticipantDescription SERVER_1_DESCRIPTION = new ASessionParticipantDescription("localhost", "9090", "Server1", ParticipantChoice.SERVER_ONLY);

}
