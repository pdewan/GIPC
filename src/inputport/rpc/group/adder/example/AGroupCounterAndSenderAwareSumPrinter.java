package inputport.rpc.group.adder.example;

import inputport.rpc.duplex.example.ADuplexCounterAndSenderAwareSummer;
import inputport.rpc.duplex.example.ARegisteredEchoer;
import inputport.rpc.duplex.example.AnotherEchoer;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.GroupRPCServerInputPort;
public class AGroupCounterAndSenderAwareSumPrinter 
			extends ADuplexCounterAndSenderAwareSummer 
			implements GroupCounterAndSenderAwareSumPrinterAndCapitalizer{
	protected CounterWithObjectValue counterWithObjectValue;
	public AGroupCounterAndSenderAwareSumPrinter(GroupRPCServerInputPort aGroupRPCServerInputPort) {
		super(aGroupRPCServerInputPort);
		counterWithObjectValue =  createOthersCounterProxy();
	}
	protected CounterWithObjectValue createOthersCounterProxy()  {
		try {
			return (CounterWithObjectValue) GroupRPCProxyGenerator.generateOthersRPCProxy((GroupRPCServerInputPort) duplexRPCServerInputPort, ACounterWithObjectValue.class, null);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected AnotherEchoer createEchoerProxy()  {
		try {
			return (AnotherEchoer) GroupRPCProxyGenerator.generateOthersRPCProxy((GroupRPCServerInputPort) duplexRPCServerInputPort, ARegisteredEchoer.class, null);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public synchronized void capitalizeAndCallbackCounter(AnotherEchoer anEchoer, String s) {
		String lastSender = duplexRPCServerInputPort.getSender(); // this value will be overridden by other sends
		String capital = s.toUpperCase();
		try {

			System.out.println("Calling counter getValue");
//			Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, true);
			int val = counter.getValue();
			System.out.println("returning from counter getValue with value:" + val);

			Object[] arrayCounterVals =  (Object[]) ((CounterWithObjectValue) counterWithObjectValue).getObjectValue();
			String counterValsToString = "(" + lastSender + " counter:" + val + " others counters:";
			for (int i = 0; i < arrayCounterVals.length; i++) {
				if (i == 0)
					counterValsToString +=  arrayCounterVals[i];
				else
					counterValsToString += "," + arrayCounterVals[i];
			}
			counterValsToString += ")";
			
			String senderPrfix = "You";
			String othersPrefix = lastSender;
			
			String messageSuffix = " said:" + capital + counterValsToString;
			if (anEchoer != null) {
				// call back procedure
				anEchoer.echo("You" + messageSuffix);
			}
			echoer.echo(lastSender + messageSuffix);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String string = stringBoundedBuffer.take();
				String lastSender = duplexRPCServerInputPort.getSender(); // this value will be overridden by other sends
//				System.out.println("Calling counter getValue()");
				int val = counter.getValue();
				Object[] arrayCounterVals =  (Object[]) ((CounterWithObjectValue) counterWithObjectValue).getObjectValue();
//				Object[] arrayCounterVals = (Object[]) objectCounterVals;
				String counterValsToString = "sender counter:" + val + " others counters:";
				for (int i = 0; i < arrayCounterVals.length; i++) {
					if (i == 0)
						counterValsToString +=  arrayCounterVals[i];
					else
						counterValsToString += "," + arrayCounterVals[i];
				}
				String message = lastSender  + "(" + counterValsToString + ")<--" + string;
				System.out.println(message);
				echoer.echo(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
//	@Override
//	public String sum(String string1, String string2) {
//		return string1 + " " + string2;
//	}
	

}
