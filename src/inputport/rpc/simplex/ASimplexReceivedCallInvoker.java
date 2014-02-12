package inputport.rpc.simplex;


import inputport.rpc.AnAbstractReceivedCallInvoker;
import inputport.rpc.RPCRegistry;
import inputport.rpc.ReceivedCallInvoker;
import util.trace.Tracer;
public class ASimplexReceivedCallInvoker extends AnAbstractReceivedCallInvoker implements ReceivedCallInvoker  {
//	RPCRegistry rpcRegistry;
	public ASimplexReceivedCallInvoker(RPCRegistry theRPCRegistry) {
		super (theRPCRegistry);
	}
	protected void handleFunctionReturn(String sender, Object retVal) {
		Tracer.error("Ignoring ret val of called method:" + retVal);
	}
	
	protected void handleProcedureReturn(String sender) {
		return;
	}
	
//	protected boolean isProcedure(Call aSerializableCall) {
//		return aSerializableCall.getMethod().getReturnType() == Void.TYPE;
//	}
//	
//		
//	protected Object invokeMethod (Method method, Object targetObject, Object[] args) {
//		try {
//			return method.invoke(targetObject, args);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	@Override
//	public void messageReceived(String aSender, Object aMessage) {
//		Call aSerializableCall = (Call) aMessage;
//		try {
//			Object targetObject = rpcRegistry.getServer(aSerializableCall.getTargetObject());
//			if (targetObject == null) {
//				throw new RPCOnUnregisteredObjectException(aSerializableCall.getTargetObject());
//			}
//			Object newVal = invokeMethod(aSerializableCall.getMethod(), targetObject, aSerializableCall.getArgs());
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
