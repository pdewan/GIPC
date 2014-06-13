package replicatedserverport.rpc.duplex.singleresponse.example;

import port.ParticipantChoice;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;

public interface GroupRPCServer2Launcher {
	public static final SessionParticipantDescription SERVER_2_DESCRIPTION = new ASessionParticipantDescription("localhost", "9091", "Server2", ParticipantChoice.SERVER_ONLY);

}
