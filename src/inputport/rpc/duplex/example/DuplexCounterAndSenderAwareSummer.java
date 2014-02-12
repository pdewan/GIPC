package inputport.rpc.duplex.example;

import inputport.rpc.simplex.example.UpperCasePrinter;

public interface DuplexCounterAndSenderAwareSummer extends UpperCasePrinter {
	public void printSumAndCallBackProcedureAndFunction(AnotherEchoer echoer, String string1, String string2);
	public Object sum(String string1, String string2);


}
