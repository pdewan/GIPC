package replicatedserverport.rpc.duplex.singleresponse.example;


public class AGroupRPCSingleReponseServer1Launcher implements GroupRPCServer1Launcher{
	
	
	public static void main (String args[]) {
		(new AGroupRPCSingleResponseServerLauncher(SERVER_1_DESCRIPTION)).launch();
//		PortMisc.displayConnections();
//		ALatecomerRelayerAndSessionsServerCreator.createServerWithTracingAndDelays(
//				ALatecomerObjectGroupSessionPortLauncher.server1Description.getName(), 
//				ALatecomerObjectGroupSessionPortLauncher.server1Description.getID());
	}
	
//	public static void oldMain (String args[]) {
//		PortMisc.displayConnections();
//		ALatecomerRelayerAndSessionsServerCreator.createServerWithTracingAndDelays(
//				AnOldLatecomerObjectGroupSessionPortLauncher.server1Description.getName(), 
//				AnOldLatecomerObjectGroupSessionPortLauncher.server1Description.getID());
//	}
	

}
