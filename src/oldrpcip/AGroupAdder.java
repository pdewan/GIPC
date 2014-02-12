package oldrpcip;

import java.lang.reflect.Method;



public class AGroupAdder implements Runnable, GroupAdder {
	protected GroupRPCServerInputPort port;
	Method method;
	Adder adderProxy;
	int p1;
	int p2;
	
	public AGroupAdder(GroupRPCServerInputPort thePort)  {
		port = thePort;
		try {
			Class[] parameterTypes = {Integer.class, Integer.class};
			method = Adder.class.getMethod("add", parameterTypes);
			adderProxy = (Adder) RPCProxyGenerator.generateOthersRPCProxy(port, Adder.class, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
	}
	public synchronized void groupAdd(int theP1, int theP2) {
//		super.messageReceived(senderName, message);	
		p1 = theP1;
		p2 = theP2;
		Thread addingThread = new Thread(this);
		addingThread.setName("adding thread");
		addingThread.start();
		
	}
	@Override
	public void run() {
//		Object[] args = {2, 2};
//		Object[] retVals = port.callOthers(Adder.class, method, args);
		Object objectRet = adderProxy.add(p1, p2);
//		Object[] retVals = (Object[]) adderProxy.add(2, 2);
		Object[] retVals = (Object[]) objectRet;
		for (Object retVal:retVals) 
			System.out.println("RETURN" + retVal);
		System.out.println(retVals);		
	}
	
}
