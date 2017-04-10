package inputport.rpc.group;

import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;

import java.lang.reflect.Method;

import port.trace.rpc.RemoteCallFinished;
import port.trace.rpc.RemoteCallInitiated;


public  class AnAllCallRPCProxyInvocationHandler extends ACachingAbstractRPCProxyInvocationHandler {
	GroupRPC fullServerRPC;
	
	public AnAllCallRPCProxyInvocationHandler (GroupRPC theRPCPort, Class theRemoteInterface, String theName ) {
		super(theRPCPort, null, theRemoteInterface, theName);
		fullServerRPC = theRPCPort;
	}
	@Override
	protected Object call(String remoteEndPoint, String name, Method method, Object[] args) {
		RemoteCallInitiated.newCase(this, destination, name, method, args);

		Object aResult = fullServerRPC.callAll(name, method, args);
		RemoteCallFinished.newCase(this, destination, name, method, args, aResult);
		return aResult;

	}
	@Override
	protected Object call(String remoteEndPoint, Method method, Object[] args) {
		return fullServerRPC.callAll(method, args);
	}
	
	@Override
	protected Object call(String remoteEndPoint, Class type, Method method, Object[] args) {
		return fullServerRPC.callAll(remoteType, method,  args);
	}
}
