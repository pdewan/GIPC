package inputport.rpc.duplex.example;

import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.ReplyRPCProxyGenerator;
import inputport.rpc.simplex.example.AnUpperCasePrinter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class ADuplexCounterAndSenderAwareSummer extends AnUpperCasePrinter implements DuplexCounterAndSenderAwareSummer, Runnable{
	protected DuplexRPCServerInputPort duplexRPCServerInputPort;
	protected AnotherCounter counter;
	protected AnotherEchoer echoer;
	Thread printingThread;
	public  final static int BUFFER_SIZE = 10;
	protected BlockingQueue<String> stringBoundedBuffer = new ArrayBlockingQueue(BUFFER_SIZE);
	public ADuplexCounterAndSenderAwareSummer(DuplexRPCServerInputPort aDuplexRPCServerInputPort) {
		super(aDuplexRPCServerInputPort);
		duplexRPCServerInputPort = aDuplexRPCServerInputPort;
		counter = createCounterProxy();
		echoer = createEchoerProxy();
		printingThread = new Thread(this);
		printingThread.setName("Counting Sum Printer");
		printingThread.start();
	}
	protected AnotherEchoer createEchoerProxy()  {
		try {
			return (AnotherEchoer) ReplyRPCProxyGenerator.generateReplyRPCProxy(duplexRPCServerInputPort, ARegisteredEchoer.class, null);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	protected AnotherCounter createCounterProxy()  {
		try {
			return (AnotherCounter) ReplyRPCProxyGenerator.generateReplyRPCProxy(duplexRPCServerInputPort, AnAnotherCounter.class, null);			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void printSumAndCallBackProcedureAndFunction(AnotherEchoer anEchoer, String p1, String p2) {
		try {
			String basicSum = sum(p1, p2);
			if (anEchoer != null) {
				// call back procedure
				anEchoer.echo("You said:" + basicSum);
				// returning received object, will it be equal to the sent object at sender site
				anEchoer.echo(anEchoer);
			}
			// calling function asynchronously
			stringBoundedBuffer.put(basicSum);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String string = stringBoundedBuffer.take();
//				System.out.println("Calling counter getValue()");
				int val =  (Integer) counter.getValue();
				//synchronous functional call
				String message = duplexRPCServerInputPort.getSender() + "(" + val + ")<--" + string;
				System.out.println(message);
				echoer.echo(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public String sum(String string1, String string2) {
		return string1 + " " + string2;
	}
	

}
