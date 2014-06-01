package sessionport.rpc.group.object.relayed.latecomer.example;

import port.ParticipantChoice;
import port.SessionChoice;
import port.sessionserver.SessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class AnAliceLatecomerGroupSessionPort {
//	public static final boolean DO_DELAY = false;
//	public static final PortLauncherSupport DELAYS_SUPPORT = null;
//	public static final boolean DO_JITTER = false;
//	public static final boolean DO_CAUSAL = false;
//	public static final ParticipantChoice PARTICIPANT_CHOICE = ParticipantChoice.CLIENT_ONLY;
//	public static final String PARTICIPANT_ID = null;




	public static void main (String[] args) {
		
		
//		(new AFlexibleSessionPortMVCClientLauncher("localhost",
//				SessionServerLauncher.SESSION_SERVER_ID,
//				SessionServerLauncher.SESSION_SERVER_NAME, 
//				ALICE_ID, ALICE_NAME, 
//				UpperCaseSession.SESSION_NAME, 
//				SessionChoice.LATECOMER_RELAYED,
//				DO_DELAY, 
//				DELAYS_SUPPORT, 
//				DO_JITTER, 
//				DO_CAUSAL, 
//				PARTICIPANT_CHOICE)).launch();
		(new AFlexibleSessionPortClientLauncher("localhost",
				SessionServerLauncher.SESSION_SERVER_ID, SessionServerLauncher.SESSION_SERVER_NAME,
//				"" + ASessionServerLauncher.SESSION_SERVER_PORT, ASessionServerLauncher.SESSION_SERVER_NAME, 
				"9100", 
				"Alice",
				SessionChoice.LATECOMER_RELAYED,
				AFlexibleSessionPortClientLauncher.DO_DELAY,
				new port.delay.example.AnAliceDelaysSupport(),
				AFlexibleSessionPortClientLauncher.DO_CAUSAL, ParticipantChoice.MEMBER
				)).launch();
	}
	
	

}
