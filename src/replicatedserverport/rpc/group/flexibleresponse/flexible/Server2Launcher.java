package replicatedserverport.rpc.group.flexibleresponse.flexible;

import port.ParticipantChoice;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;

public interface Server2Launcher {
	public static final String SERVER2 = "Static Server 2";
	public static final String HOST2 = "localhost";

//	public static final SessionParticipantDescription SERVER_2_DESCRIPTION = new ASessionParticipantDescription(HOST2, "9091", SERVER2, null);
	public static final SessionParticipantDescription SERVER_2_DESCRIPTION = new ASessionParticipantDescription(HOST2, "9091", SERVER2, ParticipantChoice.SERVER_ONLY);

}
