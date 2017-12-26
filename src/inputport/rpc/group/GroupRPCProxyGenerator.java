package inputport.rpc.group;

import inputport.rpc.DirectedRPCProxyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import trace.port.rpc.ProxyCreated;
import util.misc.RemoteReflectionUtility;


public class GroupRPCProxyGenerator extends DirectedRPCProxyGenerator {

	public static Object generateOthersRPCProxy(GroupRPC port, Class aClass, String name) {
		if (aClass == null) {
			return null;
		}
		InvocationHandler invocationHandler = new AnOthersCallRPCProxyInvocationHandler(port, aClass, name);
		Class[] remoteInterfaces = RemoteReflectionUtility.getProxyInterfaces(aClass);

		Object retVal = Proxy.newProxyInstance(aClass.getClassLoader(),
				remoteInterfaces,
                invocationHandler);
		ProxyCreated.newCase(GroupRPCProxyGenerator.class, retVal, remoteInterfaces, invocationHandler);		
		return retVal;
				
	}
	public static Object generateOthersRPCProxy(GroupRPC port, Class aClass) {
		return generateOthersRPCProxy(port,aClass, null);
				
	}
	public static Object generateAllRPCProxy(GroupRPC port, Class aClass) {
		return generateAllRPCProxy(port,aClass, null);
				
	}
	public static Object generateAllRPCProxy(GroupRPC port, Class aClass, String name) {
		if (aClass == null) {
			return null;
		}
		InvocationHandler invocationHandler = new AnAllCallRPCProxyInvocationHandler(port, aClass, name);
		Class[] remoteInterfaces = RemoteReflectionUtility.getProxyInterfaces(aClass);
		Object retVal = Proxy.newProxyInstance(aClass.getClassLoader(),
				remoteInterfaces,
                invocationHandler);
		ProxyCreated.newCase(GroupRPCProxyGenerator.class, retVal, remoteInterfaces, invocationHandler);
		
		return retVal;
				
	}
	public static Object generateAllAndMeRPCProxy(GroupRPC port, Class aClass, String name) {
		if (aClass == null) {
			return null;
		}
		InvocationHandler invocationHandler = new AnAllAndMeCallRPCProxyInvocationHandler(port, aClass, name);
		Class[] remoteInterfaces = RemoteReflectionUtility.getProxyInterfaces(aClass);
		Object retVal = Proxy.newProxyInstance(aClass.getClassLoader(),
				remoteInterfaces,
                invocationHandler);
		ProxyCreated.newCase(GroupRPCProxyGenerator.class, retVal, remoteInterfaces, invocationHandler);
		
		return retVal;
				
	}


}
