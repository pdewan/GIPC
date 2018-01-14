package util.trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReturnValueDequeuedOld extends CallInfo {
	Object returnValue;

	public ReturnValueDequeuedOld(String aMessage, Object aSource, Object aDestination, 
			String aRemoteEndPoint, RemoteCall aCall, Object returnValue) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
	}
	
	public Object getReturnValue() {
		return returnValue;
	}
	public static ReturnValueDequeuedOld newCase(Object aSource, Object aDestination,
			String aRemoteEndPoint, RemoteCall aCall, Object aReturnValue) {
    	String aMessage = "Dequeued return value " + aReturnValue + " of call: " + aCall + " with remote end point: " + aRemoteEndPoint
    			+     			" forwarded by " +  aSource + " to " + aDestination ;
    	ReturnValueDequeuedOld retVal = new ReturnValueDequeuedOld(aMessage, aSource, aDestination,
    			aRemoteEndPoint, aCall, aReturnValue);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(ReturnValueDequeued.class, true);
	}

}
