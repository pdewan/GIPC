package port.trace.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedReturnValueDequeued extends TraceableInfo {
	

	public ReceivedReturnValueDequeued(String aMessage, Object aSource, Object aQueue, Object aReturnValue) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedReturnValueDequeued newCase(Object aSource, Object aQueue,  Object aReturnValue) {
    	String aMessage =  aReturnValue + "<-" + aQueue;
    	ReceivedReturnValueDequeued retVal = new ReceivedReturnValueDequeued(aMessage, aSource, aQueue, aReturnValue);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
