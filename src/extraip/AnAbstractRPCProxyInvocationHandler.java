package extraip;

import java.lang.reflect.Method;
import java.rmi.server.RemoteObject;
import java.util.HashSet;
import java.util.Set;

import inputport.rpc.duplex.ARemoteSerializable;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.simplex.SimplexRPC;
import util.introspect.JavaIntrospectUtility;
import util.misc.HashIdentityMap;
import util.misc.IdentityMap;
import util.remote.InvocationHandlerWithProperties;
import util.trace.TraceableBus;
import util.trace.TraceableListener;
import util.trace.Tracer;
import util.trace.port.rpc.CallReceived;

public abstract class AnAbstractRPCProxyInvocationHandler implements
		InvocationHandlerWithProperties, TraceableListener {
	protected SimplexRPC rpcInputPort;
	protected Class remoteType;
	String name;
	protected String destination;
	static Method hashCodeMethod;
	Method equalsMethod;
	Method toStringMethod;
	static Method waitMethod;
	static Method notifyMethod;
	static Method notifyAllMethod;
	// should handle notify and wait also
	IdentityMap<Object, Integer> objectToHashCode = new HashIdentityMap<Object, Integer>();
	IdentityMap<Object, String> objectToString = new HashIdentityMap();
	IdentityMap<Object, Object> objectToSurrogate = new HashIdentityMap();

	boolean activityHappendedSinceLastToString = false;
	boolean equalsMethodOverridden;
	boolean toStringMethodOverridden;
	static Set<Method> localMethods = new HashSet();
	LocalRemoteReferenceTranslator translator;
	

	public AnAbstractRPCProxyInvocationHandler(SimplexRPC anRPCPort,
			String aDestination, Class aType, String aName) {
		rpcInputPort = anRPCPort;
		destination = aDestination;
		remoteType = aType;
		name = aName;
		if (aType.isInterface()) {
			Tracer.warning("Creating proxy out of interface: " + remoteType + " call to equals() and toString() may be executed more inefficiently if these Object methods have not been overriden");
		}
		if (anRPCPort instanceof DuplexRPCInputPort) {
			translator = ((DuplexRPCInputPort) anRPCPort).getLocalRemoteReferenceTranslator();
		}
		if (hashCodeMethod == null) {
			try {
				hashCodeMethod = Object.class.getMethod("hashCode");
			
				waitMethod  = Object.class.getMethod("wait");
				notifyMethod  = Object.class.getMethod("notify");
				notifyAllMethod = Object.class.getMethod("notifyAll");
				
				localMethods.add(waitMethod);
				localMethods.add(notifyMethod);
				localMethods.add(notifyAllMethod);
//				localMethods.add(hashCodeMethod);


			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		if (hashCodeMethod == null) {
			try {
				Class[] args = {Object.class};
				equalsMethod = remoteType.getMethod("equals", args);
//				System.out.println (equalsMethod.getDeclaringClass());
//				Method myEqualsMethod = this.getClass().getMethod("equals", args);
//				System.out.println("my equals declaring class:" +  myEqualsMethod.getDeclaringClass());
//				System.out.println(myEqualsMethod.getDeclaringClass() == Object.class);
				if (equalsMethod == null) { // for interface
					equalsMethod = Object.class.getMethod("equals", args);
				} 
//				else {
//					System.out.println(equalsMethod.getDeclaringClass());

//				}
				toStringMethod  = remoteType.getMethod("toString");
				if (toStringMethod == null) {
					toStringMethod  = Object.class.getMethod("toString");
				}
//				System.out.println(toStringMethod.getDeclaringClass());
				equalsMethodOverridden = !(equalsMethod.getDeclaringClass() == Object.class || equalsMethod.getDeclaringClass() == RemoteObject.class );
				toStringMethodOverridden = !(toStringMethod.getDeclaringClass() == Object.class || toStringMethod.getDeclaringClass() == RemoteObject.class);


			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
		TraceableBus.addTraceableListener(this);
	}
	
	public Class getProxyTargetClass() {
		return remoteType;
	}
	
	public String getProxyDestination() {
		return destination;
	}
	
	public String getProxyName() {
		return name;
	}
	
	public void setProxyDestination(String aDestination) {
		 destination = aDestination;
	}
	
	public void setProxyName(String aName) {
		 name = aName;
	}

	protected abstract Object call(String remoteEndPoint, String name,
			Method method, Object[] args);

	protected abstract Object call(String remoteEndPoint, Method method,
			Object[] args);

	protected abstract Object call(String remoteEndPoint, Class type,
			Method method, Object[] args);

	@Override
	public Object invoke(Object arg0, Method method, Object[] args) {
		if (localMethods.contains(method)) {
			return invokeOnSurrogate(getLocalSurrogate(arg0), method, args);
		}
		if (JavaIntrospectUtility.equalsHeader (method, hashCodeMethod)) {
			return invokeHashCode(arg0, method, args);
		} else if (JavaIntrospectUtility.equalsHeader (method, equalsMethod)) {
			return invokeEquals(arg0, method, args);
		} else if (JavaIntrospectUtility.equalsHeader (method, toStringMethod)) {
			return invokeToString(arg0, method, args);
		} 
		else {
//			activityHappendedSinceLastToString = method.getReturnType() == Void.TYPE;
			return doInvoke(arg0, method, args);
		}
	
	}
	protected Object doInvoke(Object arg0, Method method, Object[] args) {
		
		activityHappendedSinceLastToString = true; // some unknown method called, could have side effects

		try {
			if (name != null)
				return call(destination, name, method, args);
			else if (remoteType != null) {
				return call(destination, remoteType, method, args);
			} else {
				return call(destination, method, args); // will this ever be executed?
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Object invokeEquals(Object arg0, Method method, Object[] args) {
		if (!equalsMethodOverridden) 
			return arg0 == args[0];
		if (arg0 == args[0])
			return true;
		else if (!equalsMethodOverridden) 
			return false;
		else			
			return doInvoke(arg0, method, args);
	}
	
	protected Object invokeHashCode(Object arg0, Method method, Object[] args) {
		Object retVal = objectToHashCode.get(arg0);
		if (retVal == null) {
			retVal = doInvoke(arg0, method, args);
			objectToHashCode.put(arg0, (Integer) retVal);
		}
		return retVal;
	}
	protected Object invokeToString(Object arg0, Method method, Object[] args) {	
		
		// can return local toString based on surrogate if not overridden, but let us see if this causes any deadlock
		Object retVal = objectToString.get(arg0);
		
		if (retVal == null || (activityHappendedSinceLastToString && toStringMethodOverridden) ) {
			retVal = doInvoke(arg0, method, args);
			objectToString.put(arg0, (String) retVal);
			activityHappendedSinceLastToString = false;
		}
		return retVal;
	}
	protected Object invokeOnSurrogate(Object arg0, Method method, Object[] args) {
		try {
			return method.invoke(arg0, args);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Object getLocalSurrogate(Object aProxy) {
		if (translator != null)
			return translator.getRemoteSerializable(aProxy);
		Object retVal = objectToSurrogate.get(aProxy);
		if (retVal == null) {
			retVal = new ARemoteSerializable(destination, remoteType.getName(), name);
			objectToSurrogate.put(aProxy, retVal);
		}
		return retVal;
	}
	public  void newEvent(Exception aTraceable) {
		// actually should see if the message was really sent or not, and not just delayed or buffered - or should one?
		// if delayed, the toString message will also be delayed
//		if (aTraceable instanceof ByteBufferSendInfo || aTraceable instanceof ByteBufferReceiveInfo)
		if ( aTraceable instanceof CallReceived)

			activityHappendedSinceLastToString = true;
		
	}
	

}
