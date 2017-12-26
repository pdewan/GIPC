package trace.port.nio;

import java.nio.channels.SelectionKey;

import inputport.rpc.RemoteCall;
import trace.port.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class RemoteCommandExecuted extends TraceableInfo {	
	protected String command;
	public RemoteCommandExecuted(String aMessage, Object aFinder,
			String aCommand) {
		super(aMessage, aFinder);
		command = aCommand;
	}
	public static RemoteCommandExecuted newCase(Object aSource, String aCommand) {    	
		String aMessage = "Command:" + aCommand;
		RemoteCommandExecuted retVal = new RemoteCommandExecuted(aMessage, aSource, aCommand);
    	retVal.announce();
    	return retVal;
	}
}
