package inputport.rpc.duplex.echoer.example;


import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.ASimplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;





public class CathyDuplexRPCClientMVCLauncher   {
	public static final String  USER_NAME = "Cathy";
	
	
	public static void main (String[] args) {		
		(new ADuplexRPCClientMVCLauncher(USER_NAME, "localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME)).launch();
	}
	


}
