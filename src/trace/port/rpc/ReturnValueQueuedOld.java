package trace.port.rpc;

import trace.port.MessageReceiveInfo;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReturnValueQueuedOld extends MessageReceiveInfo {
	Object returnValue;

	public ReturnValueQueuedOld(String aMessage, 
			Object aSource, Object aDestination, String aRemoteEndPoint, Object returnValue) {
		super(aMessage, aSource, aDestination, aRemoteEndPoint);
	}
	
	public Object getReturnValue() {
		return returnValue;
	}
	public static ReturnValueQueuedOld newCase(Object aSource,
			Object aDestination, String aRemoteEndPoint,  Object aReturnValue) {
    	String aMessage = "Received return value " + aReturnValue + " from remote end point: " +
			aRemoteEndPoint + " and forwarding to destination:" ;
    	ReturnValueQueuedOld retVal = new ReturnValueQueuedOld(aMessage, aSource, aDestination,
    			aRemoteEndPoint, aReturnValue);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(ReturnValueQueued.class, true);
	}

}
