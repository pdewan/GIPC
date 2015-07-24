package oldrpcip;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RPCProxyGenerator {
	public static Object generateUniRPCProxy(Class localInterface, Class remoteInterfac) {
		return null;		
	}
	public static Object generateUniRPCProxy(FullClientRPC port, Class localInterface, String name) {
		InvocationHandler invocationHandler = new AUniCallRPCProxyInvocationHandler(port, localInterface, name);
		return Proxy.newProxyInstance(localInterface.getClassLoader(),
                new Class[] { localInterface },
                invocationHandler);
				
	}
	public static Object generateOthersRPCProxy(FullServerRPC port, Class localInterface, String name) {
		InvocationHandler invocationHandler = new OldAnOthersCallRPCProxyInvocationHandler(port, localInterface, name);
		return Proxy.newProxyInstance(localInterface.getClassLoader(),
                new Class[] { localInterface },
                invocationHandler);
				
	}
	
	public static void main(String[] args) {
		OldAdder adderProxy = (OldAdder) generateUniRPCProxy(null, OldAdder.class, "adder");
		int result = adderProxy.add(new Integer(5), new Integer(6));
	}

}
