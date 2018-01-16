package util.trace.port.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SelectorWokenUp extends TraceableInfo {	
	protected Selector command;
	public SelectorWokenUp(String aMessage, Object aFinder,
			Selector aSelector) {
		super(aMessage, aFinder);
		command = aSelector;
	}
	public static SelectorWokenUp newCase(Object aSource, Selector aSelector) {    	
		String aMessage = "Command:" + aSelector;
		SelectorWokenUp retVal = new SelectorWokenUp(aMessage, aSource, aSelector);
    	retVal.announce();
    	return retVal;
	}
}
