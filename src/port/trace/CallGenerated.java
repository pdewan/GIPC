package port.trace;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.Tracer;
import inputport.rpc.RemoteCall;
@DisplayToString(true)
@ComponentWidth(1000)
public class CallGenerated extends CallInfo {
	

	public CallGenerated(String aMessage, Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
	}
	
	
	public static CallGenerated newCase(Object aSource, Object aDestination, String aRemoteEndPoint, RemoteCall aCall) {
    	String aMessage = "Generated:" +  aCall + " and forwarded to " +  aDestination ;
    	CallGenerated retVal = new CallGenerated(aMessage, aSource, aDestination, aRemoteEndPoint, aCall);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
		Tracer.setKeywordDisplayStatus(CallGenerated.class, true);
	}

}
