package examples.gipc.consensus.paxos;

import inputport.datacomm.simplex.buffer.nio.AConnectCommand;
import inputport.datacomm.simplex.buffer.nio.AReadCommand;
import inputport.datacomm.simplex.buffer.nio.AWriteBoundedBuffer;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.nio.NIOTraceUtility;
import util.trace.Tracer;



public class ASessionServerLauncher  {
	
	public static void main (String args[]) {	
		NIOTraceUtility.setTracing();

		port.sessionserver.ASessionServerLauncher.main(args);
	}
	


	
	
	
}
