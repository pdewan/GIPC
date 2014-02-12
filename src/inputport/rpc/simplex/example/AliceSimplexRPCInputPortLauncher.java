package inputport.rpc.simplex.example;


public class AliceSimplexRPCInputPortLauncher {
	public static  String ALICE = "Alice";
	public static void main (String[] args) {		
		(new ASimplexRPCClientInputPortLauncher(ALICE)).launch();
	}
}
