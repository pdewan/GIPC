package inputport.rpc.duplex.example;


public class AliceDuplexRPCInputPortLauncher {
	public static  String ALICE = "Alice";
	public static void main (String[] args) {		
		(new ADuplexRPCClientInputPortLauncher(ALICE)).launch();
	}
}
