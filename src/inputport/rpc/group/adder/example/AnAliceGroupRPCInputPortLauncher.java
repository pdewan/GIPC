package inputport.rpc.group.adder.example;


public class AnAliceGroupRPCInputPortLauncher {
	public static  String ALICE = "Alice";
	public static void main (String[] args) {		
		(new AGroupRPCClientInputPortLauncher(ALICE)).launch();
	}
}
