package sessionport.datacomm.group.object.flexible.example;

import port.ParticipantChoice;
import port.sessionserver.ASessionServerLauncher;

public class ACathyGroupSessionPort {

	public static void main (String[] args) {
		(new AFlexibleSessionPortClientLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, 
				ASessionServerLauncher.SESSION_SERVER_NAME, "9102", 
				"Cathy",
				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.ACathyDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL, ParticipantChoice.SYMMETRIC_JOIN
				)).launch();
		
	}
	
	

}
