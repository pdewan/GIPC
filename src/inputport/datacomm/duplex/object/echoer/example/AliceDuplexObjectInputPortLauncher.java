package inputport.datacomm.duplex.object.echoer.example;

import inputport.datacomm.simplex.buffer.example.AnAliceSimplexBufferInputPortLauncher;

public class AliceDuplexObjectInputPortLauncher extends AnAliceSimplexBufferInputPortLauncher {
	public static void main (String[] args) {	
		
		(new ADuplexObjectClientInputPortLauncher(ALICE)).launch();
	}
}
