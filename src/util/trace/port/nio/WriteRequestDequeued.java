package util.trace.port.nio;

import inputport.nio.manager.WriteCommand;
import util.trace.TraceableInfo;

public class WriteRequestDequeued extends TraceableInfo {	
	protected WriteCommand requestResponse;
	public WriteRequestDequeued(String aMessage, Object aFinder,
			WriteCommand aRequestResponse) {
		super(aMessage, aFinder);
		requestResponse = aRequestResponse;
	}
	public static WriteRequestDequeued newCase(Object aSource, WriteCommand aCommand) {    	
		String aMessage = "Command:" + aCommand;
		WriteRequestDequeued retVal = new WriteRequestDequeued(aMessage, aSource, aCommand);
    	retVal.announce();
    	return retVal;
	}
}
