package util.trace.port.objects;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedMessageDequeued extends TraceableInfo {
	

	public ReceivedMessageDequeued(String aMessage, Object aSource, Object aQueue, Object aReceivedMessage) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedMessageDequeued newCase(Object aSource, Object aQueue,  Object aReceivedMessage) {
    	String aMessage =  aReceivedMessage + "<-" + aQueue;
    	ReceivedMessageDequeued retVal = new ReceivedMessageDequeued(aMessage, aSource, aQueue, aReceivedMessage);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
