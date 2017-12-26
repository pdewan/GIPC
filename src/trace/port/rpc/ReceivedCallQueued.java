package trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedCallQueued extends TraceableInfo {
	

	public ReceivedCallQueued(String aMessage, Object aSource, Object aQueue, Object aCall) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedCallQueued newCase(Object aSource, Object aQueue,  Object aCall) {
    	String aMessage =  aCall + "->" + aQueue;
    	ReceivedCallQueued retVal = new ReceivedCallQueued(aMessage, aSource, aQueue, aCall);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
