package inputport.rpc.duplex.example;


public class CathyDuplexRPCInputPortLauncher {
	public static  String CATHY = "Cathy";
	public static void main (String[] args) {		
		(new ADuplexRPCClientInputPortLauncher(CATHY)).launch();
	}
}
