package inputport.rpc.group;

import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;

import java.lang.reflect.Method;


public  class AnOthersCallRPCProxyInvocationHandler extends ACachingAbstractRPCProxyInvocationHandler {
	GroupRPC fullServerRPC;
	
	public AnOthersCallRPCProxyInvocationHandler (GroupRPC theRPCPort, Class theRemoteInterface, String theName ) {
		super(theRPCPort, null, theRemoteInterface, theName);
		fullServerRPC = theRPCPort;
	}
	@Override
	protected Object call(String remoteEndPoint, String name, Method method, Object[] args) {
		return fullServerRPC.callOthers(name, method, args);
	}
	@Override
	protected Object call(String remoteEndPoint, Method method, Object[] args) {
		return fullServerRPC.callOthers(method, args);
	}
	
	@Override
	protected Object call(String remoteEndPoint, Class type, Method method, Object[] args) {
		return fullServerRPC.callOthers(remoteType, method,  args);
	}
}
