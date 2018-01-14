package examples.gipc.consensus.paxos;

import inputport.nio.manager.AConnectCommand;
import inputport.nio.manager.AReadCommand;
import inputport.nio.manager.AWriteBoundedBuffer;
import util.trace.Tracer;
import util.trace.port.consensus.ProposalAcceptedNotificationReceived;
import util.trace.port.nio.NIOTraceUtility;



public class ASessionServerLauncher  {
	
	public static void main (String args[]) {	
		NIOTraceUtility.setTracing();

		port.sessionserver.ASessionServerLauncher.main(args);
	}
	


	
	
	
}
