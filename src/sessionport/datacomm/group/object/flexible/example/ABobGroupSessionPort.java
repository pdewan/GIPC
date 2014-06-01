package sessionport.datacomm.group.object.flexible.example;

import port.ParticipantChoice;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class ABobGroupSessionPort {

	public static void main (String[] args) {
		(new AFlexibleSessionPortClientLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, "9101", 
				"Bob",
				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.ABobDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL, ParticipantChoice.SYMMETRIC_JOIN
				)).launch();
	
	}
	
	

}
