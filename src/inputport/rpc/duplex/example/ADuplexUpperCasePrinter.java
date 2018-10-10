package inputport.rpc.duplex.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import inputport.datacomm.duplex.buffer.echoer.example.AReplyingUpperCaseBufferReceiveListener;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.ReplyRPCProxyGenerator;
import inputport.rpc.simplex.example.ASenderAwareUpperCasePrinter;
public class ADuplexUpperCasePrinter extends ASenderAwareUpperCasePrinter implements DuplexUpperCasePrinter{
	protected DuplexRPCServerInputPort duplexRPCServerInputPort;
	protected AnotherCounter counter;
//	protected Echoer echoer;
	public  final static int BUFFER_SIZE = 10;
	protected BlockingQueue<String> stringBoundedBuffer = new ArrayBlockingQueue(BUFFER_SIZE);
	public ADuplexUpperCasePrinter(DuplexRPCServerInputPort aDuplexRPCServerInputPort) {
		super(aDuplexRPCServerInputPort);
		duplexRPCServerInputPort = aDuplexRPCServerInputPort;
		counter = createCounterProxy();
//		echoer = createEchoerProxy();
	}
//	protected Echoer createEchoerProxy()  {
//		try {
//			return (Echoer) ReplyRPCProxyGenerator.generateReplyRPCProxy(duplexRPCServerInputPort, ARegisteredEchoer.class, null);			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	protected AnotherCounter createCounterProxy()  {
		try {
			return (AnotherCounter) ReplyRPCProxyGenerator.generateReplyRPCProxy(duplexRPCServerInputPort, AnAnotherCounter.class);			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void printUpperCaseAndCallBack(AnotherEchoer anEchoer, String aString) {
		try {
			int val = counter.getValue();
			if (anEchoer != null) {
				// call back procedure
				anEchoer.echo(AReplyingUpperCaseBufferReceiveListener.computeReply(
						computeResponse(computeResponse(aString))) + "(" + val + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
