package util.trace.port.buffer;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReplyDestinationAssociatedWithPort extends TraceableInfo {
	

	public ReplyDestinationAssociatedWithPort(String aMessage, Object aSource, String aPeerName, Object aPort) {
		super(aMessage, aSource );
	}
	
	
	public static ReplyDestinationAssociatedWithPort newCase(Object aSource, String aPeerName,  Object aPort) {
    	String aMessage =  aPeerName + "<->" + aPort;
    	ReplyDestinationAssociatedWithPort retVal = new ReplyDestinationAssociatedWithPort(aMessage, aSource, aPeerName, aPort);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
