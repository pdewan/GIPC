package port.sessionserverAndRelay.mvc.example;


import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.relay.RelayerLauncher;
import port.sessionserver.SessionServerLauncher;





public class BobThreeIndepPortClientLauncher  {
	public static final String  USER_NAME = "Bob";
	
	
	public static void main (String[] args) {
			(new AThreeIndepPortClientLauncher(USER_NAME, 
					"localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME,
					"localhost", RelayerLauncher.RELAYER_ID, RelayerLauncher.RELAYER_NAME,
					"localhost", SessionServerLauncher.SESSION_SERVER_ID, SessionServerLauncher.SESSION_SERVER_NAME,
					UpperCaseSession.SESSION_NAME
					)).launch();
	}
	


}
