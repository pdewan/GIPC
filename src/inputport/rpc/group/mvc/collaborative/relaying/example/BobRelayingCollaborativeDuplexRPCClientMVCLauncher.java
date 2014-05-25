package inputport.rpc.group.mvc.collaborative.relaying.example;


import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;





public class BobRelayingCollaborativeDuplexRPCClientMVCLauncher extends ADuplexRPCClientMVCLauncher  {
	public static final String  USER_NAME = "Bob";
	
	
	public static void main (String[] args) {
		
		(new ADuplexRPCClientRelayingCollaborativeMVCLauncher(USER_NAME, "localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME)).launch();
	}
	


}
