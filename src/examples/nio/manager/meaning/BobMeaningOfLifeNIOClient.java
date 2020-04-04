package examples.nio.manager.meaning;

import assignments.util.mainArgs.ClientArgsProcessor;
import examples.nio.manager.meaning.client.AMeaningOfLifeNIOClient;

public class BobMeaningOfLifeNIOClient {
	public static void main (String[] args) {
//		NIOTraceUtility.setTracing();
		AMeaningOfLifeNIOClient.launchClient(
				ClientArgsProcessor.getServerHost(args), 
				ClientArgsProcessor.getServerPort(args), 
				"Bob");	
	}

}
