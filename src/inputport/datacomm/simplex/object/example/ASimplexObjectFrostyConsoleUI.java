package inputport.datacomm.simplex.object.example;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.buffer.example.ASimplexBufferFrostyConsoleUI;
import inputport.datacomm.simplex.buffer.example.UserInterfaceManager;

public class ASimplexObjectFrostyConsoleUI extends ASimplexBufferFrostyConsoleUI  implements UserInterfaceManager{
//	public static final String FROSTY_PROMPT = "Please enter an excerpt from Frost's Stopping by Woods on a Snowy Evening";
	SimplexClientInputPort<Object>  simplexObjectClientPort;

	public ASimplexObjectFrostyConsoleUI(SimplexClientInputPort<Object> aClientPort) {
		simplexObjectClientPort = aClientPort;
	}
	public ASimplexObjectFrostyConsoleUI() {
	}
//	public void manageUserInterface () {
//		while (true) {
//			System.out.println(FROSTY_PROMPT);
//		    processInput (Console.readString());
//		}
//	}	
	protected void processInput(String anInput) {
		simplexObjectClientPort.send(anInput);	
	}

}
