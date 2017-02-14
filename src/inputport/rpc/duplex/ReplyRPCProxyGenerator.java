package inputport.rpc.duplex;

import inputport.rpc.DirectedRPCProxyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import port.trace.rpc.ProxyCreated;
import util.misc.RemoteReflectionUtility;

public class ReplyRPCProxyGenerator {
	public static Object generateUniRPCProxy(Class localInterface, Class remoteInterfac) {
		return null;		
	}
	public static Object generateReplyRPCProxy(DuplexRPCServerInputPort port, Class aClass, String name) {
		InvocationHandler invocationHandler = new AReplyRPCProxyInvocationHandler(port, aClass, name);
		Class[] remoteInterfaces = RemoteReflectionUtility.getProxyInterfaces(aClass);

		Object retVal = Proxy.newProxyInstance(aClass.getClassLoader(),
                remoteInterfaces,
                invocationHandler);
		ProxyCreated.newCase(DirectedRPCProxyGenerator.class, retVal, remoteInterfaces, invocationHandler);
		
		return retVal;
				
	}
	
	public static Object generateReplyRPCProxy(DuplexRPCServerInputPort port, Class aClass) {
		return generateReplyRPCProxy( port, aClass, null);
	}
	
	
	

}
