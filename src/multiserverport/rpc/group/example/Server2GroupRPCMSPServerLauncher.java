package multiserverport.rpc.group.example;

public class Server2GroupRPCMSPServerLauncher  {
	public static String server2Id = "9091";
	public static String server2Name = "Server 2";
	
	public static void main (String[] args) {
//		AGroupRPCServerLauncher.launchGroupServer(server1Id, server1Name);
		(new AGroupRPCAdderServerLauncher(server2Id, server2Name)).launch();;

	}
}
