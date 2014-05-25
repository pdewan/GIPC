package port.sessionserver.relay.late.mvc.example;


import port.sessionserver.SessionServerLauncher;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;

public class AliceTwoDepPortLatecomerClientLauncher   {
	
	public static final String  USER_NAME = "Alice";

	public static void main (String[] args) {		
		(new ATwoDepPortLatecomerClientLauncher(
				USER_NAME, 
//				"localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME,
//				"localhost", RelayerLauncher.RELAYER_ID, RelayerLauncher.RELAYER_NAME,
				"localhost", SessionServerLauncher.SESSION_SERVER_ID, SessionServerLauncher.SESSION_SERVER_NAME, UpperCaseSession.SESSION_NAME
				)).launch();
	}
}
