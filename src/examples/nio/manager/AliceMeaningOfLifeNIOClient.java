package examples.nio.manager;

import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import examples.nio.manager.client.AMeaningOfLifeNIOClient;
import util.trace.port.nio.NIOTraceUtility;

public class AliceMeaningOfLifeNIOClient {
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		AMeaningOfLifeNIOClient.launchClient(ClientArgsProcessor.getServerHost(args), 
				ClientArgsProcessor.getServerPort(args), 
				"Alice");	
	}

}
