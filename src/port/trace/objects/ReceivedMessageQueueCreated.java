package port.trace.objects;

import port.trace.GIPCQueueCreated;
import util.trace.TraceableInfo;

public class ReceivedMessageQueueCreated extends GIPCQueueCreated{
	public ReceivedMessageQueueCreated(String aMessage, Object aFinder, Object aQueue) {
		super(aMessage, aFinder, aQueue);
	}

	public static ReceivedMessageQueueCreated newCase(Object aFinder, Object aQueue, String anExplanation) {
    	String aMessage = "Queue " + aQueue + " created for " + anExplanation;
    	ReceivedMessageQueueCreated retVal =
   	    new ReceivedMessageQueueCreated(aMessage, aFinder, aQueue);
    	retVal.announce();
    	return retVal;
	}

}
