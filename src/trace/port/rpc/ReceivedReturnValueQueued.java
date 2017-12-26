package trace.port.rpc;

import inputport.rpc.RemoteCall;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.TraceableInfo;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReceivedReturnValueQueued extends TraceableInfo {
	

	public ReceivedReturnValueQueued(String aMessage, Object aSource, Object aQueue, Object aReturnValue) {
		super(aMessage, aSource );
	}
	
	
	public static ReceivedReturnValueQueued newCase(Object aSource, Object aQueue,  Object aReturnValue) {
    	String aMessage =  aReturnValue + "->" + aQueue;
    	ReceivedReturnValueQueued retVal = new ReceivedReturnValueQueued(aMessage, aSource, aQueue, aReturnValue);
   	    retVal.announce();
    	return retVal;

	}
	
	static {
//		Tracer.setKeywordDisplayStatus(CallReceived.class, true);
	}

}
