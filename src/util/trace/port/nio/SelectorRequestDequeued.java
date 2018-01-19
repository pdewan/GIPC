package util.trace.port.nio;

import inputport.nio.manager.commands.Request;
import inputport.nio.manager.commands.RequestResponse;
import util.trace.TraceableInfo;

public class SelectorRequestDequeued extends TraceableInfo {	
	protected Request requestResponse;
	public SelectorRequestDequeued(String aMessage, Object aFinder,
			Request aRequestResponse) {
		super(aMessage, aFinder);
		requestResponse = aRequestResponse;
	}
	public static SelectorRequestDequeued newCase(Object aSource, Request aCommand) {    	
		String aMessage = "Command:" + aCommand;
		SelectorRequestDequeued retVal = new SelectorRequestDequeued(aMessage, aSource, aCommand);
    	retVal.announce();
    	return retVal;
	}
}
