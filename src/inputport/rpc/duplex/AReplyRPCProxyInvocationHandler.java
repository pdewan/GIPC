package inputport.rpc.duplex;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;

import java.lang.reflect.Method;

import trace.port.rpc.RemoteCallFinished;
import trace.port.rpc.RemoteCallInitiated;
public  class AReplyRPCProxyInvocationHandler extends ACachingAbstractRPCProxyInvocationHandler {
	DuplexRPCInputPort duplexInputPort;
	String lastSender;
	
	public AReplyRPCProxyInvocationHandler (DuplexRPCServerInputPort theRPCPort, Class theRemoteInterface, String theName ) {
		super(theRPCPort, null, theRemoteInterface, theName);
		duplexInputPort = theRPCPort;
	}	
	@Override
	protected Object call(String aRemoteEndPoint, String name, Method method, Object[] args) {	
		String aCaller = AnAsynchronousSingleThreadDuplexReceivedCallInvoker.getRemoteCaller();
		if (aCaller == null)
			aCaller = duplexInputPort.getSender();
		
		RemoteCallInitiated.newCase(this, aCaller, name, method, args);

//		Object result = rpcInputPort.call(duplexInputPort.getSender(), name, method, args);
		Object result = rpcInputPort.call(aCaller, name, method, args);

		RemoteCallFinished.newCase(this, aCaller, name, method, args, result);
		return result;
		

		

	}
	@Override
	protected Object call(String aRemoteEndPoint, Method method, Object[] args) {
		return rpcInputPort.call(duplexInputPort.getSender(), method, args);
	}	
	@Override
	protected Object call(String aRemoteEndPoint, Class type, Method method, Object[] args) {
		return rpcInputPort.call(duplexInputPort.getSender(), remoteType, method,  args);
	}
}
