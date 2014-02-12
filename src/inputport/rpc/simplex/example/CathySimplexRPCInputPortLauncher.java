package inputport.rpc.simplex.example;


public class CathySimplexRPCInputPortLauncher {
	public static  String CATHY = "Cathy";
	public static void main (String[] args) {		
		(new ASimplexRPCClientInputPortLauncher(CATHY)).launch();
	}
}
