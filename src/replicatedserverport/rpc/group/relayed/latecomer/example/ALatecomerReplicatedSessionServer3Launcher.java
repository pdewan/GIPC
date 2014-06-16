package replicatedserverport.rpc.group.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ASingleReponseReplicatedLatecomerSessionServerLauncher;


public class ALatecomerReplicatedSessionServer3Launcher implements LatecomerSessionServer3Launcher {
	public static void main (String args[]) {
		(new ASingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_3_DESCRIPTION)).launch();
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
