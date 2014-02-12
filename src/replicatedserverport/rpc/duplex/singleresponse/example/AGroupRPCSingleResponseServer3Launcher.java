package replicatedserverport.rpc.duplex.singleresponse.example;


public class AGroupRPCSingleResponseServer3Launcher implements GroupRPCServer3Launcher {
	public static void main (String args[]) {
		(new AGroupRPCSingleResponseServerLauncher(SERVER_3_DESCRIPTION)).launch();
//		PortMisc.displayConnections();
//		ALatecomerRelayerAndSessionsServerCreator.createServerWithTracingAndDelays(
//				ALatecomerObjectGroupSessionPortLauncher.server1Description.getName(), 
//				ALatecomerObjectGroupSessionPortLauncher.server1Description.getID());
	}
	
//	public static void oldMain (String args[]) {
//		ALatecomerRelayerAndSessionsServerCreator.createServerWithTracingAndDelays(
//				AnOldLatecomerObjectGroupSessionPortLauncher.server3Description.getName(), 
//				AnOldLatecomerObjectGroupSessionPortLauncher.server3Description.getID());
//	}
	

}
