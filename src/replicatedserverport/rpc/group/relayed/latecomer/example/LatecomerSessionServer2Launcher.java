package replicatedserverport.rpc.group.relayed.latecomer.example;

import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;

public interface LatecomerSessionServer2Launcher {
	public static final SessionParticipantDescription SERVER_2_DESCRIPTION = new ASessionParticipantDescription("localhost", "9091", "Server2", null);

}
