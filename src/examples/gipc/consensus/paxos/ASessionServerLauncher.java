package examples.gipc.consensus.paxos;

import inputport.nio.AConnectCommand;
import inputport.nio.AReadCommand;
import inputport.nio.AWriteBoundedBuffer;
import trace.port.consensus.ProposalAcceptedNotificationReceived;
import trace.port.nio.NIOTraceUtility;
import util.trace.Tracer;



public class ASessionServerLauncher  {
	
	public static void main (String args[]) {	
		NIOTraceUtility.setTracing();

		port.sessionserver.ASessionServerLauncher.main(args);
	}
	


	
	
	
}
