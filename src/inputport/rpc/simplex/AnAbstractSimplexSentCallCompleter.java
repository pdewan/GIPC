package inputport.rpc.simplex;

import inputport.rpc.RemoteCall;

import java.lang.reflect.Method;




public abstract class AnAbstractSimplexSentCallCompleter {
	protected boolean isProcedure(Object aMessage) {
		RemoteCall serializableCall = (RemoteCall) aMessage;
		Method method = serializableCall.getMethod();
		return 	method.getReturnType().equals(Void.TYPE);
	}
	protected abstract Object returnValueOfRemoteProcedureCall(String aRemoteEndPoint, Object aMessage);
	protected abstract Object returnValueOfRemoteFunctionCall(String aRemoteEndPoint, Object aMessage);
	public Object returnValueOfRemoteMethodCall(String aRemoteEndPoint, RemoteCall aMessage) {
		if (isProcedure(aMessage))
			return returnValueOfRemoteProcedureCall(aRemoteEndPoint, aMessage);
		else
			return returnValueOfRemoteFunctionCall(aRemoteEndPoint, aMessage);

	}

	
	

}
