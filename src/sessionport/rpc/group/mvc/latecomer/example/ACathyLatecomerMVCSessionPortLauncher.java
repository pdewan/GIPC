package sessionport.rpc.group.mvc.latecomer.example;

import port.sessionserver.SessionServerLauncher;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;
import sessionport.rpc.group.mvc.flexible.example.AFlexibleSessionPortMVCClientLauncher;
import sessionport.rpc.group.mvc.flexible.example.BobSessionPort;
import sessionport.rpc.group.mvc.flexible.example.CathySessionPort;

public class ACathyLatecomerMVCSessionPortLauncher implements CathySessionPort, LatecomerClientSessionPort {

	public static void main (String[] args) {
		
		(new AFlexibleSessionPortMVCClientLauncher("localhost",
				SessionServerLauncher.SESSION_SERVER_ID,
				SessionServerLauncher.SESSION_SERVER_NAME, 
				CATHY_ID, CATHY_NAME, 
				UpperCaseSession.SESSION_NAME, 
				SESSION_CHOICE,
				DO_DELAY, 
				DELAYS_SUPPORT, 
				DO_JITTER, 
				DO_CAUSAL, 
				PARTICIPANT_CHOICE)).launch();
	}
	
	

}
