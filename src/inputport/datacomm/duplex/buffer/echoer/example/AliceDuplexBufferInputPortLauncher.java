package inputport.datacomm.duplex.buffer.echoer.example;

import inputport.datacomm.simplex.buffer.example.AnAliceSimplexBufferInputPortLauncher;

public class AliceDuplexBufferInputPortLauncher extends AnAliceSimplexBufferInputPortLauncher {
	public static void main (String[] args) {	
//		ConnectionEventListener connectionManager = new AConnectionEventManager();
//		DistEventsBus.addConnectionEventListener(connectionManager);
//		ObjectEditor.edit(connectionManager);
		(new ADuplexBufferClientInputPortLauncher(ALICE)).launch();
	}
}
