package examples.nio.manager;

import trace.port.nio.NIOTraceUtility;

public class AliceMeaningOfLifeNIOClient {
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		AMeaningOfLifeNIOClient.launchClient(ClientArgsProcessor.chooseServerHost(args), 
				ServerPort.SERVER_PORT, 
				"Alice");	
	}

}
