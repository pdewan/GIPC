package port.relay.mvc.example;


import inputport.rpc.simplex.mvc.example.ASimplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.relay.RelayerLauncher;





public class BobTwoServerClientLauncher extends ASimplexRPCClientMVCLauncher  {
	public static final String  USER_NAME = "Bob";
	
	
	public static void main (String[] args) {		
		(new ATwoServerClientLauncher(USER_NAME, 
				"localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME,
				"localhost", RelayerLauncher.RELAYER_ID, RelayerLauncher.RELAYER_NAME
				)).launch();
//		(new ADuplexRPCClientMVCLauncher(CLIENT_NAME, "localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME )).launch();

	}


}
