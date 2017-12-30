package examples.nio.manager;

import trace.port.nio.NIOTraceUtility;

public class BobMeaningOfLifeNIOClient {
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		AMeaningOfLifeNIOClient.createClient("localhost", ServerPort.SERVER_PORT, "Bob");	
	}

}
