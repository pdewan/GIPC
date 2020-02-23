package util.trace.simulation;

import util.trace.TraceableInfo;

public class LocalCommandExecuted extends TraceableInfo {	
	protected String command;
	public LocalCommandExecuted(String aMessage, String aCommand,
			Object aFinder) {
		super(aMessage, aFinder);
		command = aCommand;
	}
	public static LocalCommandExecuted newCase(Object aSource, String aCommand) {    	
		String aMessage = "Command:" + aCommand;
		LocalCommandExecuted retVal = new LocalCommandExecuted(aMessage, aCommand, aSource);
    	retVal.announce();
    	return retVal;
	}
}
