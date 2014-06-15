package replicatedserverport.rpc.group.earliestresponse.direct.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.AnEarliestReponseReplicatedSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server2Launcher;

public class EarliestResponseReplicatedSessionServer2Launcher implements Server2Launcher{
	public static void main (String args[]) {
//		Tracer.showInfo(true);
		(new AnEarliestReponseReplicatedSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();
//		PortMisc.displayConnections();
//		ALatecomerRelayerAndSessionsServerCreator.createServerWithTracingAndDelays(
//				ALatecomerObjectGroupSessionPortLauncher.server1Description.getName(), 
//				ALatecomerObjectGroupSessionPortLauncher.server1Description.getID());
	}
	
//	public static void oldMain (String args[]) {
//		PortMisc.displayConnections();
//
//		ALatecomerRelayerAndSessionsServerCreator.createServerWithTracingAndDelays(
//				AnOldLatecomerObjectGroupSessionPortLauncher.server2Description.getName(), 
//				AnOldLatecomerObjectGroupSessionPortLauncher.server2Description.getID());
//	}
	

}
