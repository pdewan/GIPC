package trace.port.rpc;

import trace.port.GIPCQueueCreated;
import util.trace.TraceableInfo;

public class ReturnValueQueueCreated extends GIPCQueueCreated{
	public ReturnValueQueueCreated(String aMessage, Object aFinder, Object aQueue) {
		super(aMessage, aFinder, aQueue);
	}

	public static ReturnValueQueueCreated newCase(Object aFinder, Object aQueue, String anExplanation) {
    	String aMessage = "Queue " + aQueue + " created for " + anExplanation;
    	ReturnValueQueueCreated retVal =
   	    new ReturnValueQueueCreated(aMessage, aFinder, aQueue);
    	retVal.announce();
    	return retVal;
	}

}
