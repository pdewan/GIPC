package sessionport.datacomm.group.object.direct.delay.jitter.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.datacomm.group.object.flexible.jitter.AJitteryFlexibleSessionPortClientLauncher;

public class CathyJitteryGroupSessionPort {

	public static void main (String[] args) {
		(new AJitteryFlexibleSessionPortClientLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, "9102", 
				"Cathy",
				SessionChoice.P2P,
				true, // shouldDelay
				new port.delay.example.ACathyDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ParticipantChoice.MEMBER, true
				)).launch();
		
	}
	
	

}
