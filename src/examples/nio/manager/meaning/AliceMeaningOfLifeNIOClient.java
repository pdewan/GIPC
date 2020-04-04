package examples.nio.manager.meaning;

import assignments.util.mainArgs.ClientArgsProcessor;
import examples.nio.manager.meaning.client.AMeaningOfLifeNIOClient;
import util.trace.port.nio.NIOTraceUtility;

public class AliceMeaningOfLifeNIOClient {
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		AMeaningOfLifeNIOClient.launchClient(ClientArgsProcessor.getServerHost(args), 
				ClientArgsProcessor.getServerPort(args), 
				"Alice");	
	}

}
