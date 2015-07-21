package extraip;


import inputport.rpc.duplex.DuplexRPC;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.ReplyRPCProxyGenerator;

import java.lang.reflect.Method;

import sessionport.rpc.duplex.relayed.example.Adder;




public class ADuplexAdder implements DuplexAdder, Runnable {
	protected DuplexRPC port;
	Method method;
	Adder adderProxy;
	int p1;
	int p2;
	
	public ADuplexAdder(DuplexRPCServerInputPort thePort)  {
		port = thePort;
		try {
//			Class[] parameterTypes = {Integer.class, Integer.class};
//			method = Adder.class.getMethod("add", parameterTypes);
//			adderProxy = (Adder) UniRPCProxyGenerator.generateUniRPCProxy(port, null, Adder.class, null);
			adderProxy = (Adder) ReplyRPCProxyGenerator.generateReplyRPCProxy(thePort, Adder.class, null);

		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	public synchronized void duplexSum(int theP1, int theP2) {
//		super.messageReceived(senderName, message);	
		p1 = theP1;
		p2 = theP2;
		Thread addingThread = new Thread(this);
		addingThread.setName("adding thread");
		addingThread.start();
//		Object objectRet = adderProxy.add(p1, p2);
//		System.out.println("RETURN" + objectRet);
		
	}
	@Override
	public void run() {
//		Object[] args = {2, 2};
//		Object[] retVals = port.callOthers(Adder.class, method, args);
		Object objectRet = adderProxy.sum(p1, p2);
		System.out.println("Duplex Adder Returning:" + objectRet);

////		Object[] retVals = (Object[]) adderProxy.add(2, 2);
//		Object[] retVals = (Object[]) objectRet;
//		for (Object retVal:retVals) 
//			System.out.println("RETURN" + retVal);
//		System.out.println(retVals);		
	}
	
}
