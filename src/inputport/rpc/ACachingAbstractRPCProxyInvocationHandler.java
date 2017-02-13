package inputport.rpc;

import inputport.rpc.duplex.ARemoteSerializable;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.simplex.SimplexRPC;

import java.lang.reflect.Method;
import java.rmi.server.RemoteObject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import port.trace.ReplayStartInfo;
import port.trace.SyncReplayEndInfo;
import port.trace.rpc.CallReceived;
import util.introspect.JavaIntrospectUtility;
import util.misc.HashIdentityMap;
import util.misc.IdentityMap;
import util.remote.InvocationHandlerWithProperties;
import util.trace.TraceableBus;
import util.trace.TraceableListener;
import util.trace.Tracer;

public abstract class ACachingAbstractRPCProxyInvocationHandler extends
ANonCachingAbstractRPCProxyInvocationHandler implements
		InvocationHandlerWithProperties, TraceableListener {
	// this is making the abstract class aware of replay, but if we
	// try to create a special class understanding replay
	// we have to change the so many subclasses inheriting
	// So this ugliness is because of lack of multiple inheritance
	// and lots of abstract classes are ugly
	// never mind, this one does not work because activity happens asynchronously
//	boolean replayMode = false; 
	static Method hashCodeMethod;
	Method equalsMethod;
	Method toStringMethod;
	static Method waitMethod;
	static Method notifyMethod;
	static Method notifyAllMethod;
	// should handle notify and wait also
	IdentityMap<Object, Integer> objectToHashCode = new HashIdentityMap<Object, Integer>();
	IdentityMap<Object, String> objectToString = new HashIdentityMap();
	IdentityMap<LocalCall, Object> callToReturnValue = new HashIdentityMap(); // can handle toString etc
	IdentityMap<Object, Object> objectToSurrogate = new HashIdentityMap();

	boolean activityHappendedSinceLastCall = false;
	boolean equalsMethodOverridden;
	boolean toStringMethodOverridden;
	static Set<Method> localMethods = new HashSet();
	LocalRemoteReferenceTranslator translator;
	
	// distinguish between efficient and not efficient handler
	public ACachingAbstractRPCProxyInvocationHandler(SimplexRPC anRPCPort,
			String aDestination, Class aType, String aName) {
		super(anRPCPort, aDestination, aType, aName);
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
				try {
					equalsMethod = remoteType.getMethod("equals", args);
				} catch (Exception e) {
					equalsMethod = null;
				}
//				Method myEqualsMethod = this.getClass().getMethod("equals", args);
//				System.out.println("my equals declaring class:" +  myEqualsMethod.getDeclaringClass());
//				System.out.println(myEqualsMethod.getDeclaringClass() == Object.class);
				if (equalsMethod == null) { // for interface
					equalsMethod = Object.class.getMethod("equals", args);
				} 
//				else {
//					System.out.println(equalsMethod.getDeclaringClass());
//
//				}
				try {
					toStringMethod  = remoteType.getMethod("toString");
				} catch (Exception e) {
					toStringMethod = null;
				}
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
	
//	public Class getProxyTargetClass() {
//		return remoteType;
//	}
//	

//	protected abstract Object call(String remoteEndPoint, String name,
//			Method method, Object[] args);
//
//	protected abstract Object call(String remoteEndPoint, Method method,
//			Object[] args);
//
//	protected abstract Object call(String remoteEndPoint, Class type,
//			Method method, Object[] args);

	@Override
	public Object invoke(Object arg0, Method method, Object[] args) {
		Tracer.info(this, " Calling Method: " + method +  "Args:" + args);
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
			return invokeOther(arg0, method, args);
		}
	
	}
	public static LocalCall getMember(Collection<LocalCall> aCollection, LocalCall call ) {
		for (LocalCall existingCall:aCollection) {
			if (existingCall.equals(call)) {
				return existingCall;
			}
		}
		return null;
	}
	protected Object invokeOther (Object arg0, Method method, Object[] args) {
		LocalCall call = null;
		if (method.getReturnType() != Void.TYPE) {
			call = new ALocalCall(arg0, method, args);
			if (!activityHappendedSinceLastCall && 
					!replayMode) { // pending replay calls are essentially new calls from caching point of view

//					outstandingCallsInReplayMode == 0) { // pending replay calls are essentially new calls from caching point of view

			Collection<LocalCall> previousCalls = callToReturnValue.keyCollection();
			LocalCall existingCall = getMember(previousCalls, call);
			if (existingCall != null) {
				Tracer.info (this, "No activity since last call and not in replay mode, returning cached value:");

				return callToReturnValue.get(existingCall);
			}
			}
		}
		Object retVal = super.invoke(arg0, method, args);
		
		if (call != null) {
			callToReturnValue.put(call, retVal);
		}
		// this should be expandable in some way
		if (method.getReturnType() != Void.TYPE && hasNoSideEffects(method) 
				
		) {
			Tracer.info(this, "Invoked a call and activity happened sice last call = false");
			activityHappendedSinceLastCall = false;
		} else {
			activityHappendedSinceLastCall = true;

		}
		return retVal;
	}
	protected boolean hasNoSideEffects (Method method) {
		return JavaIntrospectUtility.isTableGetMethod(method) || 
		JavaIntrospectUtility.isGetter(method) ||
		JavaIntrospectUtility.isSizeMethod(method) ||
		JavaIntrospectUtility.isElementAtMethod(method) ||
		JavaIntrospectUtility.isGetMethod(method);
		
	}
//	protected Object doInvoke(Object arg0, Method method, Object[] args) {
////		LocalCall call = null;
////		if (method.getReturnType() != Void.TYPE) {
////			call = new ALocalCall(arg0, method, args);
////			if (!activityHappendedSinceLastCall) {
////			Collection<LocalCall> previousCalls = callToReturnValue.keyCollection();
////			LocalCall existingCall = getMember(previousCalls, call);
////			if (existingCall != null) {
////				return callToReturnValue.get(existingCall);
////			}
////			}
////		}
////		Object retVal;
//		activityHappendedSinceLastCall = true; 
//
//		try {
//			if (name != null)
//				return call(destination, name, method, args);
//			else if (remoteType != null) {
//				return call(destination, remoteType, method, args);
//			} else {
//				return call(destination, method, args); // will this ever be executed?
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
////		activityHappendedSinceLastCall = true; 
////		if (method.getReturnType() != null) {
////			callToReturnValue.put(call, retVal);
////		}
////		return retVal;
//	}
	
	protected Object invokeEquals(Object arg0, Method method, Object[] args) {
//		if (!equalsMethodOverridden) 
//			return arg0 == args[0];
		if (arg0 == args[0])
			return true;
		else if (!equalsMethodOverridden) 
			return false;
		else			
			return super.invoke(arg0, method, args);
	}
	
	protected Object invokeHashCode(Object arg0, Method method, Object[] args) {
		Object retVal = objectToHashCode.get(arg0);
		if (retVal == null) {
			retVal = super.invoke(arg0, method, args);
			objectToHashCode.put(arg0, (Integer) retVal);
		}
		return retVal;
	}
	protected Object invokeToString(Object arg0, Method method, Object[] args) {	
		
		// can return local toString based on surrogate if not overridden, but let us see if this causes any deadlock
		Object retVal = objectToString.get(arg0);
		
		if (retVal == null || (activityHappendedSinceLastCall && toStringMethodOverridden) ) {
			retVal = super.invoke(arg0, method, args);
			objectToString.put(arg0, (String) retVal);
			activityHappendedSinceLastCall = false; // must occur after rather than before call
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
	
	// the non replay stuff should be in superclass as it has nothing to do with caching
//	public  void newEvent(Exception aTraceable) {
//		// actually should see if the message was really sent or not, and not just delayed or buffered - or should one?
//		// if delayed, the toString message will also be delayed
////		if (aTraceable instanceof ByteBufferSendInfo || aTraceable instanceof ByteBufferReceiveInfo)
//		if ( aTraceable instanceof CallReceived) {
//			if (replayMode) {
//				outstandingCallsInReplayMode++;
//				Tracer.info(this, "Incremented outstandingCallsInReplayMode to: " + outstandingCallsInReplayMode);
//
//			}
//
//			activityHappendedSinceLastCall = true;
//		} else if (aTraceable instanceof ReceivedCallEnded) {
//			if (replayMode) {
//				outstandingCallsInReplayMode--;
//				Tracer.info(this, "Decremented outstandingCallsInReplayMode to: " + outstandingCallsInReplayMode);
//			}
//		}
//		else if (aTraceable instanceof ReplayStartInfo ) {
//			replayMode = true;
//		} else if (aTraceable instanceof AsyncReplayEndInfo) {
//			// this does not work because the replay happens 
//			// asynchronously
//			replayMode = false; 
//			if 	(outstandingCallsInReplayMode == 0) {
//				AsyncReplayEndInfo asyncReplayEndInfo = (AsyncReplayEndInfo) aTraceable;
//				SyncReplayEndInfo.newCase(asyncReplayEndInfo.getFinder(), asyncReplayEndInfo.getReplayMessageList());
//			}
//
////			if (outstandingCallsInReplayMode != 0) {
////				Tracer.error("Number of outstanding calls)
////			}
//		} 
//		
//	}
	
	// the non replay stuff should be in superclass as it has nothing to do with caching
		public  void newEvent(Exception aTraceable) {
//			super.newEvent(aTraceable);
			// actually should see if the message was really sent or not, and not just delayed or buffered - or should one?
			// if delayed, the toString message will also be delayed
//			if (aTraceable instanceof ByteBufferSendInfo || aTraceable instanceof ByteBufferReceiveInfo)
			if ( aTraceable instanceof CallReceived) {
//				if (replayMode) {
//					outstandingCallsInReplayMode++;
//					Tracer.info(this, "Incremented outstandingCallsInReplayMode to: " + outstandingCallsInReplayMode);
//
//				}
//				Tracer.setKeywordPrintStatus(this, true);
				Tracer.info(this, "Call received so activity happened since last call = true");
				activityHappendedSinceLastCall = true;
			} 
//			else if (aTraceable instanceof ReceivedCallEnded) {
//				if (replayMode) {
//					outstandingCallsInReplayMode--;
//					Tracer.info(this, "Decremented outstandingCallsInReplayMode to: " + outstandingCallsInReplayMode);
//				}
//			}
			else if (aTraceable instanceof ReplayStartInfo ) {
				replayMode = true;
			} else if (aTraceable instanceof SyncReplayEndInfo) {
				replayMode = false;
			}
//			} else if (aTraceable instanceof AsyncReplayEndInfo) {
//				// this does not work because the replay happens 
//				// asynchronously
//				replayMode = false; 
//				if 	(outstandingCallsInReplayMode == 0) {
//					AsyncReplayEndInfo asyncReplayEndInfo = (AsyncReplayEndInfo) aTraceable;
//					SyncReplayEndInfo.newCase(asyncReplayEndInfo.getFinder(), asyncReplayEndInfo.getReplayMessageList());
//				}
//
////				if (outstandingCallsInReplayMode != 0) {
////					Tracer.error("Number of outstanding calls)
////				}
//			} 
			
		}
	

}
