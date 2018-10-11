package util.trace.port.rpc;

import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedCallDequeued extends TraceableInfo {
	

	public ReceivedCallDequeued(String aMessage, Object aSource, Object aQueue, Object aCall) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedCallDequeued newCase(Object aSource, Object aQueue,  Object aCall) {
    	String aMessage =  aCall + "<-" + aQueue;
    	ReceivedCallDequeued retVal = new ReceivedCallDequeued(aMessage, aSource, aQueue, aCall);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
