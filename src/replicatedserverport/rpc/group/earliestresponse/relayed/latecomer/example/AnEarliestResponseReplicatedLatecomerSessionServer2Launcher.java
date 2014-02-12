package replicatedserverport.rpc.group.earliestresponse.relayed.latecomer.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.AnEarliestReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server2Launcher;
import util.trace.Tracer;

public class AnEarliestResponseReplicatedLatecomerSessionServer2Launcher implements Server2Launcher{
	public static void main (String args[]) {
		Tracer.showInfo(true);
		(new AnEarliestReponseReplicatedLatecomerSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();
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
