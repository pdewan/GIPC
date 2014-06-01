package sessionport.datacomm.group.object.direct.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class AliceP2PMemberGroupSessionPort {

	public static void main (String[] args) {
		(new AFlexibleSessionPortClientLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, 
				"9100", 
				"Alice",
				SessionChoice.P2P,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL, ParticipantChoice.MEMBER
				)).launch();
	}
	
	

}
