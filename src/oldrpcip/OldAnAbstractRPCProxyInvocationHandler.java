package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class OldAnAbstractRPCProxyInvocationHandler implements InvocationHandler {
	FullClientRPC rpcClientInputPort;
	Class remoteInterface;
	String name;
	public OldAnAbstractRPCProxyInvocationHandler (FullClientRPC theRPCPort, Class theRemoteInterface, String theName ) {
		rpcClientInputPort = theRPCPort;
		remoteInterface = theRemoteInterface;
		name = theName;
	}
	abstract Serializable call(String name, Method method, Object[] args);
	abstract Serializable call(Method method, Object[] args);
	abstract Serializable call(Class type, Method method, Object[] args);

	@Override
	public Object invoke(Object arg0, Method method, Object[] args)
			throws Throwable {
//		if (!(args instanceof Object[]))
//			return null;
		if (name != null) 
			return call(name, method, args);
		else if (remoteInterface != null) {
			return call(remoteInterface, method, args);
		} else {
			return call(method, args);
		}
	}

}
