package examples.nio.manager;

import examples.nio.manager.client.AMeaningOfLifeNIOClient;
import examples.nio.manager.client.ClientArgsProcessor;
import examples.nio.manager.server.ServerPort;
import util.trace.port.nio.NIOTraceUtility;

public class BobMeaningOfLifeNIOClient {
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		AMeaningOfLifeNIOClient.launchClient(
				ClientArgsProcessor.chooseServerHost(args), 
				ServerPort.SERVER_PORT, 
				"Bob");	
	}

}
