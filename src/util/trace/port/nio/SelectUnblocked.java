package util.trace.port.nio;

import java.nio.channels.Selector;

import util.trace.TraceableInfo;

public class SelectUnblocked extends TraceableInfo {	
	protected Selector command;
	public SelectUnblocked(String aMessage, Object aFinder,
			Selector aSelector) {
		super(aMessage, aFinder);
		command = aSelector;
	}
	public static SelectUnblocked newCase(Object aSource, Selector aSelector) {    	
		String aMessage =  aSelector.toString();
		SelectUnblocked retVal = new SelectUnblocked(aMessage, aSource, aSelector);
    	retVal.announce();
    	return retVal;
	}
}
