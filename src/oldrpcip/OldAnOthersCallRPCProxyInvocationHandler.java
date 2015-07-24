package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;

public  class OldAnOthersCallRPCProxyInvocationHandler extends OldAnAbstractRPCProxyInvocationHandler {
	FullServerRPC fullServerRPC;
	
	public OldAnOthersCallRPCProxyInvocationHandler (FullServerRPC theRPCPort, Class theRemoteInterface, String theName ) {
		super(theRPCPort, theRemoteInterface, theName);
		fullServerRPC = theRPCPort;
	}
	@Override
	Serializable call(String name, Method method, Object[] args) {
		return fullServerRPC.callOthers(name, method, args);
	}
	@Override
	Serializable call(Method method, Object[] args) {
		return fullServerRPC.callOthers(method, args);
	}
	
	@Override
	Serializable call(Class type, Method method, Object[] args) {
		return fullServerRPC.callOthers(remoteInterface, method,  args);
	}
}
