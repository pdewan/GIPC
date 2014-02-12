package replicatedserverport.rpc.duplex.singleresponse.example;

import util.trace.Tracer;

public class AGroupRPCSingleResponseServer2Launcher implements GroupRPCServer2Launcher{
	public static void main (String args[]) {
//		Tracer.showInfo(true);
		(new AGroupRPCSingleResponseServerLauncher(SERVER_2_DESCRIPTION)).launch();
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
