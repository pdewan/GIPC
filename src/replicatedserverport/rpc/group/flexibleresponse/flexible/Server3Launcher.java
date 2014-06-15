package replicatedserverport.rpc.group.flexibleresponse.flexible;

import port.ParticipantChoice;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;

public interface Server3Launcher {
	public static final String SERVER3 = "Static Server 3";
	public static final String HOST3 = "localhost";
	public static final SessionParticipantDescription SERVER_3_DESCRIPTION = new ASessionParticipantDescription(HOST3, "9092", SERVER3, ParticipantChoice.SERVER_ONLY);

}
