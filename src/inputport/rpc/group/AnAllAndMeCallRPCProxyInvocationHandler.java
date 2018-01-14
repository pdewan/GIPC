package inputport.rpc.group;

import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;

import java.lang.reflect.Method;

import util.trace.port.rpc.RemoteCallFinished;
import util.trace.port.rpc.RemoteCallInitiated;


public  class AnAllAndMeCallRPCProxyInvocationHandler extends ACachingAbstractRPCProxyInvocationHandler {
	GroupRPC fullServerRPC;
	
	public AnAllAndMeCallRPCProxyInvocationHandler (GroupRPC theRPCPort, Class theRemoteInterface, String theName ) {
		super(theRPCPort, null, theRemoteInterface, theName);
		fullServerRPC = theRPCPort;
	}
	@Override
	protected Object call(String remoteEndPoint, String name, Method method, Object[] args) {
//		RemoteCallInitiated.newCase(this, destination, name, method, args);

		Object aResult = fullServerRPC.callAllAndMe(name, method, args);
//		RemoteCallFinished.newCase(this, destination, name, method, args, aResult);
		return aResult;
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
