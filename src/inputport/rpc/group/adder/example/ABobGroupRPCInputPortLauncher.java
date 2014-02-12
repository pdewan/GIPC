package inputport.rpc.group.adder.example;


public class ABobGroupRPCInputPortLauncher {
	public static  String BOB = "Bob";
	public static void main (String[] args) {		
		(new AGroupRPCClientInputPortLauncher(BOB)).launch();
	}
}
