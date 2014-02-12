package inputport.rpc.duplex.example;


public class BobDuplexRPCInputPortLauncher {
	public static  String BOB = "Bob";
	public static void main (String[] args) {		
		(new ADuplexRPCClientInputPortLauncher(BOB)).launch();
	}
}
