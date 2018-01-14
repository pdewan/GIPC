package util.trace.port.rpc;

import util.trace.TraceableInfo;
import util.trace.port.GIPCQueueCreated;

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
