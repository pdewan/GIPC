package util.trace.port;

import inputport.datacomm.ReceiveListener;
import util.trace.TraceableInfo;

public class ReceiveListenerRegistered extends TraceableInfo{
	public ReceiveListenerRegistered(String aMessage, Object aFinder, ReceiveListener aListener) {
		super(aMessage, aFinder);
	}

	public static ReceiveListenerRegistered newCase(Object aFinder,  ReceiveListener aListener) {
    	String aMessage = aListener.toString();
    	ReceiveListenerRegistered retVal =
   	    new ReceiveListenerRegistered(aMessage, aFinder, aListener);
    	retVal.announce();
    	return retVal;
	}

}
