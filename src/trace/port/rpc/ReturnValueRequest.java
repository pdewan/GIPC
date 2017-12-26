package trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReturnValueRequest extends CallInfo {
	

	public ReturnValueRequest(String aMessage, Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
	}
	
	
	public static ReturnValueRequest newCase(Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
    	String aMessage = "Waitng for return value of call: " + aCall + " with remote end point: " + aRemoteEndPoint
    			+     			" forwarded by " +  aSource + " to " + aDestination ;
    	ReturnValueRequest retVal = new ReturnValueRequest(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(ReturnValueRequest.class, true);
	}

}
