package inputport.rpc.group.example;


public class CathyGroupRPCInputPortLauncher {
	public static  String CATHY = "Cathy";
	public static void main (String[] args) {		
		(new AGroupRPCClientInputPortLauncher(CATHY)).launch();
	}
}
