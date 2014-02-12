package inputport.datacomm.group.buffer.example;

import inputport.datacomm.duplex.buffer.echoer.example.BobDuplexBufferInputPortLauncher;

public class ABobGroupBufferInputPortLauncher extends BobDuplexBufferInputPortLauncher  {
	public static void main (String[] args) {		
//		(new ADuplexBufferClientInputPortLauncher()).launch(BOB);
		BobDuplexBufferInputPortLauncher.main(args);
	}
}
