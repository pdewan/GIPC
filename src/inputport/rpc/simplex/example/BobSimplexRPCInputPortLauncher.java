package inputport.rpc.simplex.example;


public class BobSimplexRPCInputPortLauncher {
	public static  String BOB = "Bob";
	public static void main (String[] args) {		
		(new ASimplexRPCClientInputPortLauncher(BOB)).launch();
	}
}
