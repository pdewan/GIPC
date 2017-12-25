package inputport.datacomm.simplex.buffer.example;

import port.trace.nio.NIOTraceUtility;


public class AnAliceSimplexBufferInputPortLauncher {
	public static  String ALICE = "Alice";
	public static void main (String[] args) {	
		NIOTraceUtility.setTracing();
		(new ASimplexBufferClientInputPortLauncher(ALICE)).launch();
	}
}
