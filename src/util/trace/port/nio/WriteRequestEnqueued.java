package util.trace.port.nio;

import inputport.nio.manager.WriteCommand;
import util.trace.TraceableInfo;

public class WriteRequestEnqueued extends TraceableInfo {	
	protected WriteCommand requestResponse;
	public WriteRequestEnqueued(String aMessage, Object aFinder,
			WriteCommand aRequestResponse) {
		super(aMessage, aFinder);
		requestResponse = aRequestResponse;
	}
	public static WriteRequestEnqueued newCase(Object aSource, WriteCommand aCommand) {    	
		String aMessage = "Command:" + aCommand;
		WriteRequestEnqueued retVal = new WriteRequestEnqueued(aMessage, aSource, aCommand);
    	retVal.announce();
    	return retVal;
	}
}
