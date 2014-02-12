package sessionport.datacomm.group.object.direct.delayed.causal.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;

public class ABobCausalGroupSessionPort {

	public static void main (String[] args) {
		(new AFlexibleSessionPortClientLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, "9101", 
				"Bob",
				SessionChoice.P2P,
				true, // should delay
				new port.delay.example.ABobDelaysSupport(),
				true // causal
, ParticipantChoice.SYMMETRIC_JOIN
				)).launch();
	
	}
	
	

}
