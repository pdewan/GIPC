package inputport.rpc.simplex;

import java.lang.reflect.Method;

import inputport.rpc.RemoteCall;




public abstract class AnAbstractSimplexSentCallCompleter {
	protected boolean isProcedure(Object aMessage) {
		RemoteCall serializableCall = (RemoteCall) aMessage;
		Method method = serializableCall.getMethod();
		return 	method.getReturnType().equals(Void.TYPE);
	}
	protected abstract Object getReturnValueOfRemoteProcedureCall(String aRemoteEndPoint, Object aMessage);
	protected abstract Object getReturnValueOfRemoteFunctionCall(String aRemoteEndPoint, Object aMessage);
	/*
	 * Have not changed name of this method
	 */
	public Object returnValueOfRemoteMethodCall(String aRemoteEndPoint, RemoteCall aMessage) {
		if (isProcedure(aMessage))
			return getReturnValueOfRemoteProcedureCall(aRemoteEndPoint, aMessage);
		else
			return getReturnValueOfRemoteFunctionCall(aRemoteEndPoint, aMessage);

	}

	
	

}
