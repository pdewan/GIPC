package examples.nio.manager;

import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import examples.nio.manager.client.AMeaningOfLifeNIOClient;
import util.trace.port.nio.NIOTraceUtility;

public class BobMeaningOfLifeNIOClient {
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		AMeaningOfLifeNIOClient.launchClient(
				ClientArgsProcessor.getServerHost(args), 
				ServerPort.SERVER_PORT, 
				"Bob");	
	}

}
