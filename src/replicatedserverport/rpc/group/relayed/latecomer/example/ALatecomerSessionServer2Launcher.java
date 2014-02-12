package replicatedserverport.rpc.group.relayed.latecomer.example;

import util.trace.Tracer;

public class ALatecomerSessionServer2Launcher implements LatecomerSessionServer2Launcher{
	public static void main (String args[]) {
//		Tracer.showInfo(true);
		(new ALatecomerSessionServerLauncher(SERVER_2_DESCRIPTION)).launch();
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
