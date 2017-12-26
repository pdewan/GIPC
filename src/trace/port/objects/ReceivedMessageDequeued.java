package trace.port.objects;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedMessageDequeued extends TraceableInfo {
	

	public ReceivedMessageDequeued(String aMessage, Object aSource, Object aQueue, Object aCall) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedMessageDequeued newCase(Object aSource, Object aQueue,  Object aCall) {
    	String aMessage =  aCall + "<-" + aQueue;
    	ReceivedMessageDequeued retVal = new ReceivedMessageDequeued(aMessage, aSource, aQueue, aCall);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
