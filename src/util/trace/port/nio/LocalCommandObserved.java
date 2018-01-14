package util.trace.port.nio;

import java.nio.channels.SelectionKey;

import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

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
