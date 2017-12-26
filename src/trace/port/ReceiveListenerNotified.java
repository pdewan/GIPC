package trace.port;

import inputport.datacomm.ReceiveListener;
import util.trace.TraceableInfo;

public class ReceiveListenerNotified extends TraceableInfo{
	public ReceiveListenerNotified(String aMessage, Object aFinder, ReceiveListener aListener, Object anObject) {
		super(aMessage, aFinder);
	}

	public static ReceiveListenerNotified newCase(Object aFinder, ReceiveListener aListener, Object anObject) {
    	String aMessage = aListener + "<-" + anObject;
    	ReceiveListenerNotified retVal =
   	    new ReceiveListenerNotified(aMessage, aFinder, aListener, anObject);
    	retVal.announce();
    	return retVal;
	}

}
