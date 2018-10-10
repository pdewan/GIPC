package util.trace.port.nio;

import util.trace.TraceableInfo;

public class RemoteCommandExecuted extends TraceableInfo {	
	protected String command;
	public RemoteCommandExecuted(String aMessage, String aCommand,
			Object aFinder) {
		super(aMessage, aFinder);
		command = aCommand;
	}
	public static RemoteCommandExecuted newCase(Object aSource, String aCommand) {    	
		String aMessage = "Command:" + aCommand;
		RemoteCommandExecuted retVal = new RemoteCommandExecuted(aMessage, aCommand, aSource);
    	retVal.announce();
    	return retVal;
	}
}
