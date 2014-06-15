package replicatedserverport.rpc.group.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ASingleReponseReplicatedLatecomerSessionServerLauncher;


public class ALatecomerSessionServer1Launcher implements LatecomerSessionServer1Launcher{
	
	// This is the old version, some factories do nto seem right
//	public static void main (String args[]) {
//		(new ALatecomerSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();
////		PortMisc.displayConnections();
////		ALatecomerRelayerAndSessionsServerCreator.createServerWithTracingAndDelays(
////				ALatecomerObjectGroupSessionPortLauncher.server1Description.getName(), 
////				ALatecomerObjectGroupSessionPortLauncher.server1Description.getID());
//	}
	
	public static void main (String args[]) {
		(new ASingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	
//	public static void oldMain (String args[]) {
//		PortMisc.displayConnections();
//		ALatecomerRelayerAndSessionsServerCreator.createServerWithTracingAndDelays(
//				AnOldLatecomerObjectGroupSessionPortLauncher.server1Description.getName(), 
//				AnOldLatecomerObjectGroupSessionPortLauncher.server1Description.getID());
//	}
	

}
