package inputport.rpc.group.example;


public class AliceGroupRPCInputPortLauncher {
	public static  String ALICE = "Alice";
	public static void main (String[] args) {	
		(new AGroupRPCClientInputPortLauncher(ALICE)).launch();

//		(new AnOldGroupRPCClientInputPortLauncher(ALICE)).launch();
	}
}
