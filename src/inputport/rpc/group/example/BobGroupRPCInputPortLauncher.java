package inputport.rpc.group.example;


public class BobGroupRPCInputPortLauncher {
	public static  String BOB = "Bob";
	public static void main (String[] args) {		
		(new AGroupRPCClientInputPortLauncher(BOB)).launch();
	}
}
