package multiserverport.rpc.group.example;

public class Server1GroupRPCMSPServerLauncher  {
	public static String server1Id = "9090";
	public static String server1Name = "Server 1";
	
	public static void main (String[] args) {
//		AGroupRPCServerLauncher.launchGroupServer(server1Id, server1Name);
		(new AGroupRPCAdderServerLauncher(server1Id, server1Name)).launch();;

	}
}
