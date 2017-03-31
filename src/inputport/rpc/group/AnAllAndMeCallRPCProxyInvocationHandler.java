package inputport.rpc.group;

import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;

import java.lang.reflect.Method;


public  class AnAllAndMeCallRPCProxyInvocationHandler extends ACachingAbstractRPCProxyInvocationHandler {
	GroupRPC fullServerRPC;
	
	public AnAllAndMeCallRPCProxyInvocationHandler (GroupRPC theRPCPort, Class theRemoteInterface, String theName ) {
		super(theRPCPort, null, theRemoteInterface, theName);
		fullServerRPC = theRPCPort;
	}
	@Override
	protected Object call(String remoteEndPoint, String name, Method method, Object[] args) {
		return fullServerRPC.callAllAndMe(name, method, args);
	}
	@Override
	protected Object call(String remoteEndPoint, Method method, Object[] args) {
		return fullServerRPC.callAllAndMe(method, args);
	}
	
	@Override
	protected Object call(String remoteEndPoint, Class type, Method method, Object[] args) {
		return fullServerRPC.callAllAndMe(remoteType, method,  args);
	}
}
