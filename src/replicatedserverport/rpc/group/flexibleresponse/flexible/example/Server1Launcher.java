package replicatedserverport.rpc.group.flexibleresponse.flexible.example;

import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;

public interface Server1Launcher {
	public static final String SERVER1 = "Static Server 1";
	public static final String HOST1 = "localhost";
	public static final SessionParticipantDescription SERVER_1_DESCRIPTION = new ASessionParticipantDescription(HOST1, "9090", SERVER1, null);

}
