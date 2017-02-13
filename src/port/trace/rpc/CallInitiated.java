package port.trace.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
@DisplayToString(true)
@ComponentWidth(1000)
public class CallInitiated extends CallInfo {
	

	public CallInitiated(String aMessage, Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
		display = true;
	}
	
	
	public static CallInitiated newCase(Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
    	String aMessage = "Queued: " + aCall + " with remote end point: " + aRemoteEndPoint + 
    			" forwarded by " +  aSource + " to " + aDestination ;
    	CallInitiated retVal = new CallInitiated(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallInitiated.class, true);
	}

}
