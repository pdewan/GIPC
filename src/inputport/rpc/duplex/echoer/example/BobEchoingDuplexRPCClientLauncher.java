package inputport.rpc.duplex.echoer.example;


import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;





public class BobEchoingDuplexRPCClientLauncher   {
	public static final String  USER_NAME = "Bob";
	
	
	public static void main (String[] args) {		
		(new AnEchoingDuplexRPCClientLauncher(USER_NAME, "localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME )).launch();

	}
	


}
