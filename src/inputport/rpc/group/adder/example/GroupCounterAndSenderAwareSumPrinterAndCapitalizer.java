package inputport.rpc.group.adder.example;

import inputport.rpc.duplex.example.AnotherEchoer;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;

public interface GroupCounterAndSenderAwareSumPrinterAndCapitalizer extends
	DuplexCounterAndSenderAwareSummer{
	public void capitalizeAndCallbackCounter(AnotherEchoer anEchoer, String s);

}
