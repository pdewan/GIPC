package inputport.rpc.simplex.example;

import inputport.datacomm.simplex.object.ASimplexObjectClientInputPort;
import inputport.datacomm.simplex.object.example.ASimplexObjectFrostyConsoleUI;
import inputport.rpc.simplex.SimplexRPCClientInputPort;
import util.misc.Console;

public class ASimplexRPCFrostyConsoleUI extends ASimplexObjectFrostyConsoleUI {
	protected UpperCasePrinter upperCasePrinter;
	public ASimplexRPCFrostyConsoleUI(UpperCasePrinter anUpperCasePrinter) {
		upperCasePrinter = anUpperCasePrinter;
	}
	
	protected void processInput(String anInput) {
	    upperCasePrinter.printUppercase(anInput);  
	}

}
