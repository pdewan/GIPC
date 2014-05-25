package inputport.rpc.duplex.mvc.multiuser.example;


import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;





public class AliceDuplexRPCClientMVCLauncher extends ADuplexRPCClientMVCLauncher  {
	public static final String  USER_NAME = "Alice";	
	public static void main (String[] args) {		
		(new ADuplexRPCClientMVCLauncher(USER_NAME, "localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME )).launch();
	}
}
