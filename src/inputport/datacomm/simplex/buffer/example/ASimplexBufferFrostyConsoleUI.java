package inputport.datacomm.simplex.buffer.example;

import java.nio.ByteBuffer;

import inputport.datacomm.simplex.SimplexClientInputPort;
import util.misc.Console;

public class ASimplexBufferFrostyConsoleUI implements UserInterfaceManager{
	public static final String FROSTY_PROMPT = "Words of Rober Frost please!";
	SimplexClientInputPort<ByteBuffer>  simplexBufferClientPort;

	public ASimplexBufferFrostyConsoleUI(SimplexClientInputPort<ByteBuffer> aClientPort) {
		simplexBufferClientPort = aClientPort;
	}
	public ASimplexBufferFrostyConsoleUI() {
	}
	public void manageUserInterface () {
		while (true) {
			System.out.println(FROSTY_PROMPT);
		    processInput (Console.readString());
		}
	}	
	protected void processInput(String anInput) {
		ByteBuffer message =  ByteBuffer.wrap(anInput.getBytes());
		simplexBufferClientPort.send(message);	
	}

}
