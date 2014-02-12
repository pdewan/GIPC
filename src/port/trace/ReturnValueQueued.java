package port.trace;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.Tracer;
import inputport.rpc.RemoteCall;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReturnValueQueued extends MessageReceiveInfo {
	Object returnValue;

	public ReturnValueQueued(String aMessage, 
			Object aSource, Object aDestination, String aRemoteEndPoint, Object returnValue) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
	}
	
	public Object getReturnValue() {
		return returnValue;
	}
	public static ReturnValueQueued newCase(Object aSource,
			Object aDestination, String aRemoteEndPoint,  Object aReturnValue) {
    	String aMessage = "Received return value " + aReturnValue + " from remote end point: " +
			aRemoteEndPoint + " and forwarding to destination:" ;
    	ReturnValueQueued retVal = new ReturnValueQueued(aMessage, aSource, aDestination,
    			aRemoteEndPoint, aReturnValue);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(ReturnValueQueued.class, true);
	}

}
