package replicatedserverport.rpc.group.earliestresponse.direct.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AnEarliestReponseReplicatedSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server1Launcher;

public class EarliestResponseReplicatedSessionServer1Launcher implements Server1Launcher{
	
	
	public static void main (String args[]) {
		(new AnEarliestReponseReplicatedSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();
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
