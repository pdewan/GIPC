package inputport.rpc;


import java.lang.reflect.Method;

import port.trace.AConnectionEvent;
import port.trace.ConnectiontEventBus;







public abstract class AnAbstractReceivedCallInvoker implements ReceivedCallInvoker  {
	RPCRegistry rpcRegistry;
	public AnAbstractReceivedCallInvoker(RPCRegistry theRPCRegistry) {
		rpcRegistry = theRPCRegistry;
		announceConnectionEvents();
	}
	void announceConnectionEvents() {
		for (String method:rpcRegistry.registeredMethodNames()) {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, method, false));
		}
	}
	abstract protected void handleFunctionReturn(String aSender, Object aRetVal);	
	abstract protected void handleProcedureReturn(String aSender);	
	public static boolean isProcedure(RemoteCall aSerializableCall) {
		return aSerializableCall.getMethod().getReturnType() == Void.TYPE;
	}		
	protected Object invokeMethod (Method method, Object targetObject, Object[] args) {
		try {
			return method.invoke(targetObject, args);
		} catch (Exception e) {
			// ideally we should put these exceptions in a bounded buffer to somehow propagate to the caller 
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void messageReceived(String aSender, Object aMessage) {
		RemoteCall<String> aCall = (RemoteCall<String>) aMessage;
		try {
			Object targetObject = rpcRegistry.getServerObject(aCall.getTargetObject());
			if (targetObject == null) {
				throw new RPCOnUnregisteredObjectException(aCall.getTargetObject());
			}
			Object newVal = invokeMethod(aCall.getMethod(), targetObject, aCall.getArgs());
			if (isProcedure(aCall))
				handleProcedureReturn(aSender);
			else
				handleFunctionReturn(aSender, newVal);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
//	@Override
//	public void messageReceived(String aSender, Object aMessage) {
//		SerializableCall aSerializableCall = (SerializableCall) aMessage;
//		try {
//			Object targetObject = rpcRegistry.getServer(aSerializableCall.getTargetObject());
//			if (targetObject == null) {
//				throw new RPCOnUnregisteredObjectException(aSerializableCall.getTargetObject());
//			}
//			Object newVal = invokeMethod(aSerializableCall.getSerializableMethod().getMethod(), targetObject, aSerializableCall.getArgs());
//			if (isProcedure(aSerializableCall))
//				handleProcedureReturn(aSender);
//			else
//				handleFunctionReturn(aSender, newVal);
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//	}
}
