package sessionport.rpc.group.mvc.direct.example;

import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionServerLauncher;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;
import sessionport.rpc.group.mvc.flexible.example.AFlexibleSessionPortMVCServerLauncher;
import sessionport.rpc.group.mvc.flexible.example.ServerSessionPort;

public class ADirectSessionPortMVCServerLauncher
	implements ServerDirectSessionPort, SimplexRPCServerMVCLauncher {
	public static void main (String[] args) {
		(new AFlexibleSessionPortMVCServerLauncher("localhost",
				SessionServerLauncher.SESSION_SERVER_ID,
				SessionServerLauncher.SESSION_SERVER_NAME, 
				MVC_SERVER_ID, 
				MVC_SERVER_NAME, 
				UpperCaseSession.SESSION_NAME, 
				SESSION_CHOICE, 
				DO_DELAY,
				DELAYS_SUPPORT, 
				DO_JITTER,
                DO_CAUSAL, PARTICIPANT_CHOICE)).launch();
	}
}
