package extraip;

import inputport.rpc.duplex.DuplexRPC;

public class ACallingBackSquareAdder implements CallingBackSquareAdder, Runnable {
	DuplexRPC port;
	RemoteAdder remoteAdder;
	int p1, p2;
	public ACallingBackSquareAdder(DuplexRPC thePort)  {
		port = thePort;
		try {
//			Class[] parameterTypes = {Integer.class, Integer.class};
//			method = Adder.class.getMethod("add", parameterTypes);
//			adderProxy = (Adder) UniRPCProxyGenerator.generateUniRPCProxy(port, Adder.class, null);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public void add (RemoteAdder aRemoteAdder, int aP1, int aP2 ) {
		p1 = aP1;
		p2 = aP2;
		remoteAdder = aRemoteAdder;
		Thread addingThread = new Thread(this);
		addingThread.setName("adding thread");
		addingThread.start();
//		int retVal = remoteAdder.add(7, 8);
//		System.out.println("Remote Adder result:" + retVal);		
	}
	public void run() {
//		Object[] args = {2, 2};
//		Object[] retVals = port.callOthers(Adder.class, method, args);
		Object objectRet = remoteAdder.sum(p1*p1, p2*p2);
		System.out.println("Square Addeer returning:" + objectRet);
	}

	

}
