package inputport.rpc.simplex;

import inputport.rpc.RemoteCall;
import util.trace.Tracer;



public class ASimplexSentCallCompleter extends AnAbstractSimplexSentCallCompleter implements SimplexSentCallCompleter {
	public ASimplexSentCallCompleter() {
		
	}
	/*
	 * Old name
	 */
	protected Object returnValueOfRemoteProcedureCall(String aRemoteEndPoint, Object aMessage) {
		return getReturnValueOfRemoteProcedureCall(aRemoteEndPoint, aMessage);
	}
	protected Object getReturnValueOfRemoteProcedureCall(String aRemoteEndPoint, Object aMessage) {
		return null;
	}
	protected Object getReturnValueOfRemoteFunctionCall(String aRemoteEndPoint, Object aMessage) {
		RemoteCall serializableCall = (RemoteCall) aMessage;
		Tracer.error("Null returned for call on simplex port of method: " + 
				serializableCall.getMethod().getName() );
		return null;
	}	
}
