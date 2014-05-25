package inputport.rpc.simplex.example;

import inputport.datacomm.simplex.object.example.ASimplexObjectFrostyConsoleUI;

public class ASimplexRPCFrostyConsoleUI extends ASimplexObjectFrostyConsoleUI {
	protected UpperCasePrinter upperCasePrinter;
	public ASimplexRPCFrostyConsoleUI(UpperCasePrinter anUpperCasePrinter) {
		upperCasePrinter = anUpperCasePrinter;
	}
	
	protected void processInput(String anInput) {
	    upperCasePrinter.printUppercase(anInput);  
	}

}
