package util.trace.port;

import util.trace.TraceableInfo;

public class GIPCQueueCreated extends TraceableInfo{
	Object queue;
	public GIPCQueueCreated(String aMessage, Object aFinder, Object aQueue) {
		super(aMessage, aFinder);
		queue = aQueue;
	}

	public static GIPCQueueCreated newCase(Object aFinder, Object aQueue, String anExplanation) {
    	String aMessage = "Queue " + aQueue + " created for " + anExplanation;
    	GIPCQueueCreated retVal =
   	    new GIPCQueueCreated(aMessage, aFinder, aQueue);
    	retVal.announce();
    	return retVal;
	}

}
