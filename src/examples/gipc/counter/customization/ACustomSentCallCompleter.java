package examples.gipc.counter.customization;

import inputport.rpc.duplex.ADuplexSentCallCompleter;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
/**
 * Simply traces the relevant calls in its GIPC superclass
 */
public class ACustomSentCallCompleter extends ADuplexSentCallCompleter	{
	
	public ACustomSentCallCompleter(DuplexRPCInputPort aPort, LocalRemoteReferenceTranslator aRemoteHandler) {
		super(aPort, aRemoteHandler);		
	}	
		
	@Override
	public Object waitForReturnValue(String aRemoteEndPoint) {
		Object retVal = super.waitForReturnValue(aRemoteEndPoint);		
		System.out.println (aRemoteEndPoint +  "-->" + retVal);
		return retVal;
	}
	@Override
	protected void returnValueReceived(String aRemoteEndPoint, Object message) {
		System.out.println ("Processing return value of call:" + aRemoteEndPoint + "." + message);
		super.returnValueReceived(aRemoteEndPoint, message);		
	}
	protected Object getReturnValueOfRemoteFunctionCall(String aRemoteEndPoint, Object aMessage) {
		System.out.println ("getReturnValueOfRemoteFunctionCall called");
		Object retVal = super.getReturnValueOfRemoteFunctionCall(aRemoteEndPoint, aMessage);
		System.out.println ("Returning:" + retVal);
		return retVal;
	}
	protected Object getReturnValueOfRemoteProcedureCall(String aRemoteEndPoint, Object aMessage) {
		System.out.println ("getReturnValueOfRemoteProcedureCall called");
		Object retVal = super.getReturnValueOfRemoteProcedureCall(aRemoteEndPoint, aMessage);
		System.out.println ("Returning:" + retVal);
		return retVal;
	}
}
