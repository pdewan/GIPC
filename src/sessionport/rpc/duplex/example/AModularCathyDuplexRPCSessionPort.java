package sessionport.rpc.duplex.example;

import port.ParticipantChoice;
import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.SessionServerLauncher;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.rpc.group.mvc.direct.example.ClientDirectSessionPort;
import sessionport.rpc.group.mvc.flexible.example.AFlexibleSessionPortMVCClientLauncher;
import sessionport.rpc.group.mvc.flexible.example.CathySessionPort;

public class AModularCathyDuplexRPCSessionPort implements CathySessionPort, ClientDirectSessionPort {
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
		(new AModularDuplexRPCSessionPortLauncher(
				"localhost",
				SessionServerLauncher.SESSION_SERVER_ID,
				SessionServerLauncher.SESSION_SERVER_NAME, 
				CATHY_ID, CATHY_NAME, 
				"Test Session", 
				SESSION_CHOICE,
				DO_DELAY, 
				DELAYS_SUPPORT, 
				DO_JITTER, 
				DO_CAUSAL, 
				PARTICIPANT_CHOICE)).launch();
	}


}
