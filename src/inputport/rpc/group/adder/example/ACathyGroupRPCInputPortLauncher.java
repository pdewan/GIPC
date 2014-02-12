package inputport.rpc.group.adder.example;


public class ACathyGroupRPCInputPortLauncher {
	public static  String CATHY = "Cathy";
	public static void main (String[] args) {		
		(new AGroupRPCClientInputPortLauncher(CATHY)).launch();
	}
}
