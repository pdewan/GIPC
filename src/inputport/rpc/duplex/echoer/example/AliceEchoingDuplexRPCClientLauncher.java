package inputport.rpc.duplex.echoer.example;


import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.ASimplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;





public class AliceEchoingDuplexRPCClientLauncher  {
	public static final String  USER_NAME = "Alice";	
	public static void main (String[] args) {		
		(new AnEchoingDuplexRPCClientLauncher(USER_NAME, "localhost", EchoingDuplexServerLauncher.ECHOER_SERVER_ID, EchoingDuplexServerLauncher.ECHOER_SERVER_NAME )).launch();
	}
}
