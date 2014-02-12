package replicatedserverport.rpc.group.earliestresponse.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AnEarliestReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server3Launcher;

public class AnEarliestResponseReplicatedLatecomerSessionServer3Launcher implements Server3Launcher {
	public static void main (String args[]) {
		(new AnEarliestReponseReplicatedLatecomerSessionServerLauncher(SERVER_3_DESCRIPTION)).launch();
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
