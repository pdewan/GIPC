package sessionport.datacomm.group.object.relayed.latecomer.jitter.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.datacomm.group.object.flexible.jitter.example.AJitteryFlexibleSessionPortClientLauncher;
import util.trace.Tracer;

public class AliceJitteryLatecomerGroupSessionPortLauncher {

	public static void main (String[] args) {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, true);
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
