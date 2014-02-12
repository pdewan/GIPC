package inputport.rpc.group.mvc.collaborative.example;


import inputport.rpc.duplex.mvc.multiuser.example.BobDuplexRPCClientMVCLauncher;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;





public class BobCollaborativeDuplexRPCClientMVCLauncher extends ADuplexRPCClientMVCLauncher  {
	public static final String  USER_NAME = "Alice";	
	public static void main (String[] args) {	
		BobDuplexRPCClientMVCLauncher.main(args);
//		(new ADuplexRPCClientMVCLauncher(USER_NAME, "localhost", SimplexRPCServerMVCLauncher.MVC_PORT, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME )).launch();
	}
}
