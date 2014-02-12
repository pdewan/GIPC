package inputport.rpc.group.adder.example;

import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;
import inputport.rpc.duplex.example.AnotherEchoer;

public interface GroupCounterAndSenderAwareSumPrinterAndCapitalizer extends
	DuplexCounterAndSenderAwareSummer{
	public void capitalizeAndCallbackCounter(AnotherEchoer anEchoer, String s);

}
