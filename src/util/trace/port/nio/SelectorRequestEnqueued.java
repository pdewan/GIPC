package util.trace.port.nio;

import inputport.nio.manager.Request;
import inputport.nio.manager.RequestResponse;
import util.trace.TraceableInfo;

public class SelectorRequestEnqueued extends TraceableInfo {	
	protected Request requestResponse;
	public SelectorRequestEnqueued(String aMessage, Object aFinder,
			Request aRequestResponse) {
		super(aMessage, aFinder);
		requestResponse = aRequestResponse;
	}
	public static SelectorRequestEnqueued newCase(Object aSource, Request aCommand) {    	
		String aMessage = "Command:" + aCommand;
		SelectorRequestEnqueued retVal = new SelectorRequestEnqueued(aMessage, aSource, aCommand);
    	retVal.announce();
    	return retVal;
	}
}
