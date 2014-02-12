package port.trace;

import util.trace.TraceableInfo;

public class QueueCreated extends TraceableInfo{
	Object queue;
	public QueueCreated(Object aFinder, Object aQueue) {
		super("Queue " + aQueue + " created by " + aFinder, aFinder);
		queue = aQueue;
	}

	public static QueueCreated newCase(Object aFinder, Object aQueue) {
//    	String aMessage = "Queue " + aQueue + " created by " + aFinder;
    	QueueCreated retVal =
   	    new QueueCreated(aFinder, aQueue);
    	retVal.announce();
    	return retVal;
	}

}
