package inputport.rpc.duplex.example;

import inputport.rpc.simplex.example.ASimplexRPCFrostyConsoleUI;

public class ADuplexRPCFrostyConsoleUI extends ASimplexRPCFrostyConsoleUI {
//	public static final String FROSTY_PROMPT = "Please enter an excerpt from Frost's Stopping by Woods on a Snowy Evening";
//	UpperCasePrinter upperCasePrinter;
	AnotherEchoer echoer;
	AnotherCounter counter;
	public ADuplexRPCFrostyConsoleUI(DuplexUpperCasePrinter anUpperCasePrinter, AnotherEchoer anEchoer, AnotherCounter aCounter) {
		super(anUpperCasePrinter);
		echoer = anEchoer;
		counter = aCounter;
	}
	
	protected void processInput(String anInput) {
	    upperCasePrinter.printUppercase(anInput);  
	    counter.increment(1);
	   ((DuplexUpperCasePrinter) upperCasePrinter).printUpperCaseAndCallBack(echoer, anInput);
	}

}
