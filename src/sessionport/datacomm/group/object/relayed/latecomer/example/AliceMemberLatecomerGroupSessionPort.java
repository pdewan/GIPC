package sessionport.datacomm.group.object.relayed.latecomer.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.ASessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import util.trace.port.ConnectionEventManagerFactory;
import bus.uigen.ObjectEditor;

public class AliceMemberLatecomerGroupSessionPort {

	public static void main (String[] args) {
//		ObjectEditor.edit(ConnectionEventManagerFactory.getConnectionManager());
		(new AFlexibleSessionPortClientLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, 
				"9100", 
				"Alice",
				SessionChoice.LATECOMER_RELAYED,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL, ParticipantChoice.MEMBER
				)).launch();
	}
	
	

}
