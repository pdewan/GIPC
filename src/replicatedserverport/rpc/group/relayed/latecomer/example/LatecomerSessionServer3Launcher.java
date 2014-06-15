package replicatedserverport.rpc.group.relayed.latecomer.example;

import port.ParticipantChoice;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;

public interface LatecomerSessionServer3Launcher {
	public static final SessionParticipantDescription SERVER_3_DESCRIPTION = new ASessionParticipantDescription("localhost", "9092", "Server3", ParticipantChoice.SERVER_ONLY);

}
