package util.trace.port.objects;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedMessageQueued extends TraceableInfo {
	

	public ReceivedMessageQueued(String aMessage, Object aSource, Object aQueue, Object aCall) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedMessageQueued newCase(Object aSource, Object aQueue,  Object aReceivedMessage) {
    	String aMessage =  aReceivedMessage + "->" + aQueue;
    	ReceivedMessageQueued retVal = new ReceivedMessageQueued(aMessage, aSource, aQueue, aReceivedMessage);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
