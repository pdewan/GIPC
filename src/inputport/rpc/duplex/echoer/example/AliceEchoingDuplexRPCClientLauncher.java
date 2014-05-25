package inputport.rpc.duplex.echoer.example;







public class AliceEchoingDuplexRPCClientLauncher  {
	public static final String  USER_NAME = "Alice";	
	public static void main (String[] args) {		
		(new AnEchoingDuplexRPCClientLauncher(USER_NAME, "localhost", EchoingDuplexServerLauncher.ECHOER_SERVER_ID, EchoingDuplexServerLauncher.ECHOER_SERVER_NAME )).launch();
	}
}
