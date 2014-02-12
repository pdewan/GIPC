package inputport.datacomm.duplex.object.echoer.example;

import inputport.datacomm.simplex.buffer.example.ABobSimplexBufferInputPortLauncher;

public class BobDuplexObjectInputPortLauncher extends ABobSimplexBufferInputPortLauncher {
	public static void main (String[] args) {		
		(new ADuplexObjectClientInputPortLauncher(BOB)).launch();
	}
}
