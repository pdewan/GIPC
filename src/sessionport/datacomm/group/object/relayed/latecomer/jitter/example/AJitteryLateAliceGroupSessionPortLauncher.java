package sessionport.datacomm.group.object.relayed.latecomer.jitter.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.example.AFlexibleSessionPortClientLauncher;
import sessionport.datacomm.group.object.flexible.jitter.example.AJitteryFlexibleSessionPortClientLauncher;

public class AJitteryLateAliceGroupSessionPortLauncher {

	public static void main (String[] args) {
		(new AJitteryFlexibleSessionPortClientLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, 
				"9100", 
				"Alice",
				SessionChoice.LATECOMER_RELAYED,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL,
				ParticipantChoice.MEMBER, true
				)).launch();
	}
	
	

}
