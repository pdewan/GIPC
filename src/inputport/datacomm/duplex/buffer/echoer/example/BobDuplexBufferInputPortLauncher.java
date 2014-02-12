package inputport.datacomm.duplex.buffer.echoer.example;

import inputport.datacomm.simplex.buffer.example.ABobSimplexBufferInputPortLauncher;

public class BobDuplexBufferInputPortLauncher extends ABobSimplexBufferInputPortLauncher {
	public static void main (String[] args) {		
		(new ADuplexBufferClientInputPortLauncher(BOB)).launch();
	}
}
