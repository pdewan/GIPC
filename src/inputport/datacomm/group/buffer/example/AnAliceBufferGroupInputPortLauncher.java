package inputport.datacomm.group.buffer.example;

import inputport.datacomm.duplex.buffer.echoer.example.AliceDuplexBufferInputPortLauncher;

public class AnAliceBufferGroupInputPortLauncher 
	extends inputport.datacomm.duplex.buffer.echoer.example.AliceDuplexBufferInputPortLauncher  {
	public static void main (String[] args) {		
//		(new ADuplexBufferClientInputPortLauncher()).launch(ALICE);
		AliceDuplexBufferInputPortLauncher.main(args);
	}
}
