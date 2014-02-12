package inputport.rpc.duplex.example;

import inputport.rpc.simplex.example.UpperCasePrinter;

public interface DuplexUpperCasePrinter extends UpperCasePrinter {
	public void printUpperCaseAndCallBack(AnotherEchoer echoer, String string);
//	public Object sum(String string1, String string2);


}
