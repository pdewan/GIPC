package port.old;

import inputport.rpc.duplex.example.AnotherEchoer;

public interface DuplexCounterAndSenderAwareSummer extends SumPrinter {
	public void printSumAndCallBackProcedureAndFunction(AnotherEchoer echoer, String string1, String string2);
	public Object sum(String string1, String string2);


}
