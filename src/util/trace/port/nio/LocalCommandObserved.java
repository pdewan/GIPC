package util.trace.port.nio;

import util.trace.TraceableInfo;

public class LocalCommandObserved extends TraceableInfo {	
	protected String command;
	public LocalCommandObserved(String aMessage, Object aFinder,
			String aCommand) {
		super(aMessage, aFinder);
		command = aCommand;
	}
	public static LocalCommandObserved newCase(Object aSource, String aCommand) {    	
		String aMessage = "Command:" + aCommand;
		LocalCommandObserved retVal = new LocalCommandObserved(aMessage, aSource, aCommand);
    	retVal.announce();
    	return retVal;
	}
}
