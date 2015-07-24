package oldrpcip;

import java.lang.reflect.Method;



public class OldAGroupAdder implements Runnable, GroupAdder {
	protected GroupRPCServerInputPort port;
	Method method;
	OldAdder adderProxy;
	int p1;
	int p2;
	
	public OldAGroupAdder(GroupRPCServerInputPort thePort)  {
		port = thePort;
		try {
			Class[] parameterTypes = {Integer.class, Integer.class};
			method = OldAdder.class.getMethod("add", parameterTypes);
			adderProxy = (OldAdder) RPCProxyGenerator.generateOthersRPCProxy(port, OldAdder.class, null);
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
