package inputport.rpc.duplex;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import util.misc.RemoteReflectionUtility;

public class ReplyRPCProxyGenerator {
	public static Object generateUniRPCProxy(Class localInterface, Class remoteInterfac) {
		return null;		
	}
	public static Object generateReplyRPCProxy(DuplexRPCServerInputPort port, Class aClass, String name) {
		InvocationHandler invocationHandler = new AReplyRPCProxyInvocationHandler(port, aClass, name);
		Class[] remoteInterfaces = RemoteReflectionUtility.getProxyInterfaces(aClass);

		return Proxy.newProxyInstance(aClass.getClassLoader(),
                remoteInterfaces,
                invocationHandler);
				
	}
	
	public static Object generateReplyRPCProxy(DuplexRPCServerInputPort port, Class aClass) {
		return generateReplyRPCProxy( port, aClass, null);
	}
	
	
	

}
