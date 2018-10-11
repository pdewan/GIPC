package examples.nio.manager;

import assignments.util.mainArgs.ClientArgsProcessor;
import examples.nio.manager.client.AMeaningOfLifeNIOClient;

public class BobMeaningOfLifeNIOClient {
	public static void main (String[] args) {
//		NIOTraceUtility.setTracing();
		AMeaningOfLifeNIOClient.launchClient(
				ClientArgsProcessor.getServerHost(args), 
				ClientArgsProcessor.getServerPort(args), 
				"Bob");	
	}

}
