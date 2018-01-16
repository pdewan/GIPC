package util.trace.port.nio;

import inputport.nio.manager.ReadCommand;
import inputport.nio.manager.WriteCommand;
import util.trace.TraceableInfo;

public class ReadRequestCreated extends TraceableInfo {	
	protected ReadCommand requestResponse;
	public ReadRequestCreated(String aMessage, Object aFinder,
			ReadCommand aRequestResponse) {
		super(aMessage, aFinder);
		requestResponse = aRequestResponse;
	}
	public static ReadRequestCreated newCase(Object aSource, ReadCommand aCommand) {    	
		String aMessage = "Command:" + aCommand;
		ReadRequestCreated retVal = new ReadRequestCreated(aMessage, aSource, aCommand);
    	retVal.announce();
    	return retVal;
	}
}
