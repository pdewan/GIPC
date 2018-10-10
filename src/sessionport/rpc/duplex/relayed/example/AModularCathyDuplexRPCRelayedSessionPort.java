package sessionport.rpc.duplex.relayed.example;

import port.ParticipantChoice;
import port.sessionserver.SessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.rpc.group.mvc.flexible.example.CathySessionPort;

public class AModularCathyDuplexRPCRelayedSessionPort implements CathySessionPort {
	public static void main(String[] args) {
//		ADuplexRPCSessionPortLauncher.launchSessionPartipant( "9093", "Cathy", ParticipantChoice.MEMBER);		
//		(new CopyOfADuplexRPCSessionPortLauncher(AFlexibleSessionPortClientLauncher.SESSION_SERVER_HOST,
//				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, 
//				"9100", 
//				"Alice",
//				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
//				AFlexibleSessionPortClientLauncher.DO_DELAY,
//				new port.delay.example.AnAliceDelaysSupport(),
//				AFlexibleSessionPortClientLauncher.DO_CAUSAL, ParticipantChoice.SYMMETRIC_JOIN
//				)).launch();
		(new AModularDuplexRPCRelayedSessionPortLauncher(
				"localhost",
				SessionServerLauncher.SESSION_SERVER_ID,
				SessionServerLauncher.SESSION_SERVER_NAME, 
				CATHY_ID, CATHY_NAME, 
				"Test Session", 
				AFlexibleSessionPortClientLauncher.SESSION_CHOICE,
				AFlexibleSessionPortClientLauncher.DO_DELAY, 
				null, 
				false, 
				AFlexibleSessionPortClientLauncher.DO_CAUSAL, 
				ParticipantChoice.MEMBER)).launch();
	}


}
