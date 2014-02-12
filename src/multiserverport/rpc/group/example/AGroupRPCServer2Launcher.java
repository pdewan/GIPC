package multiserverport.rpc.group.example;

public class AGroupRPCServer2Launcher  {
	public static String server2Id = "9091";
	public static String server2Name = "Server 2";
	
	public static void main (String[] args) {
		AGroupRPCServerLauncher.launchGroupServer(server2Id, server2Name);
	}
}
