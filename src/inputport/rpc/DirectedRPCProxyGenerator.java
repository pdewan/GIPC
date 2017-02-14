package inputport.rpc;



import inputport.QueryablePort;
import inputport.rpc.duplex.example.AnAnotherCounter;
import inputport.rpc.duplex.example.AnotherCounter;
import inputport.rpc.simplex.SimplexRPC;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import port.trace.rpc.ProxyCreated;
import util.misc.RemoteReflectionUtility;

public class DirectedRPCProxyGenerator {
	static boolean shortCircuitLocalCallsToRemotes = true;

	public static boolean isShortCircuitLocalCallsToRemotes() {
		return shortCircuitLocalCallsToRemotes;
	}
	public static void setDoShortCircuitLocalCallsToRemotes(
			boolean newVal) {
		DirectedRPCProxyGenerator.shortCircuitLocalCallsToRemotes = newVal;
	}
	public static Object getLocalRegisteredObject (SimplexRPC aPort, String aDestination, Class aClass, String anObjectName) {
		String actualObjectName = anObjectName == null? aClass.getName(): anObjectName;
		if (aPort instanceof QueryablePort) {
			if (((QueryablePort) aPort).getLocalName().equals(aDestination)) {
				if (aPort instanceof RPCRegistry) {
					return ((RPCRegistry) aPort).getServerObject(actualObjectName);
					
				} else {
					return null;
				}
			} 
		}
		return null;
	}
	public static Object generateRPCProxyOrGetLocalRegisteredObject(SimplexRPC aPort, String aDestination, Class aClass, String anObjectName) {	
		String actualObjectName = anObjectName == null? aClass.getName(): anObjectName;
		if (aPort instanceof QueryablePort) {
			if (((QueryablePort) aPort).getLocalName().equals(aDestination)) {
				if (aPort instanceof RPCRegistry) {
					return ((RPCRegistry) aPort).getServerObject(actualObjectName);
					
				} else {
					return null;
				}
			} 
		}
		return generateRPCProxyAndDoNotGetLocalRegisteredObject(aPort, aDestination, aClass, anObjectName);		
	}
	public static Object generateRPCProxyAndDoNotGetLocalRegisteredObject(SimplexRPC aPort, String aDestination, Class aClass, String anObjectName) {	
//		String actualObjectName = anObjectName == null? aClass.getName(): anObjectName;
//		if (aPort instanceof QueryablePort) {
//			if (((QueryablePort) aPort).getLocalName().equals(aDestination)) {
//				if (aPort instanceof RPCRegistry) {
//					return ((RPCRegistry) aPort).getServerObject(actualObjectName);
//					
//				} else {
//					return null;
//				}
//			} 
//		}
		Class[] remoteInterfaces = RemoteReflectionUtility.getProxyInterfaces(aClass);

		InvocationHandler invocationHandler = new AnRPCProxyInvocationHandler(aPort, aDestination, aClass, anObjectName);
		Object retVal = Proxy.newProxyInstance(aClass.getClassLoader(),
                remoteInterfaces,
                invocationHandler);	
		ProxyCreated.newCase(DirectedRPCProxyGenerator.class, retVal, remoteInterfaces, invocationHandler);
		
		return retVal;
	}
	public static Object generateRPCProxy(SimplexRPC aPort, String aDestination, Class aClass, String anObjectName) {	
		if (isShortCircuitLocalCallsToRemotes())
			return generateRPCProxyOrGetLocalRegisteredObject(aPort, aDestination, aClass, anObjectName);

		else
			return generateRPCProxyAndDoNotGetLocalRegisteredObject(aPort, aDestination, aClass, anObjectName);

	}

	
	public static Object generateRPCProxy(SimplexRPC aPort, Class aClass) {		
		return generateRPCProxy(aPort, null, aClass, null);	
	}
	
	public static Object generateRPCProxy(SimplexRPC aPort, String aDestination, Class aClass) {		
		return generateRPCProxy(aPort, aDestination, aClass, null);	
	}
	
	public static Object generateRPCProxy(SimplexRPC aPort, Class aClass, String anObjectName) {		
		return generateRPCProxy(aPort, null, aClass, anObjectName);	
	}

	
	public static void main(String[] args) {
		AnotherCounter counterProxy = (AnotherCounter) generateRPCProxy(null, null, AnAnotherCounter.class, "counter");
		int result = counterProxy.getValue();
	}

}
