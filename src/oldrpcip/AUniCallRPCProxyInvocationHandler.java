package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;

public  class AUniCallRPCProxyInvocationHandler extends AnAbstractRPCProxyInvocationHandler {
	
	public AUniCallRPCProxyInvocationHandler (FullClientRPC theRPCPort, Class theRemoteInterface, String theName ) {
		super(theRPCPort, theRemoteInterface, theName);
	}
	@Override
	Serializable call(String name, Method method, Object[] args) {
		return rpcClientInputPort.call(name, method, args);
	}
	@Override
	Serializable call(Method method, Object[] args) {
		return rpcClientInputPort.call(method, args);
	}
	
	@Override
	Serializable call(Class type, Method method, Object[] args) {
		return rpcClientInputPort.call(remoteInterface, method,  args);
	}

}
