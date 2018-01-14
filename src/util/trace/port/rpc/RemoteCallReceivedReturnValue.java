package util.trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class RemoteCallReceivedReturnValue extends TraceableInfo {
	

	public RemoteCallReceivedReturnValue(String aMessage, Object aSource, Object aReturnValue) {
		super(aMessage, aSource );
	}
	
	
	public static RemoteCallReceivedReturnValue newCase(Object aSource,  Object aReturnValue) {
    	String aMessage = "Returned:"  + aReturnValue;
    	RemoteCallReceivedReturnValue retVal = new RemoteCallReceivedReturnValue(aMessage, aSource, aReturnValue);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
