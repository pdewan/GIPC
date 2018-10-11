package util.trace.port.nio;

import java.nio.channels.Selector;

import util.trace.TraceableInfo;

public class SelectorWokenUp extends TraceableInfo {	
	protected Selector command;
	public SelectorWokenUp(String aMessage, Object aFinder,
			Selector aSelector) {
		super(aMessage, aFinder);
		command = aSelector;
	}
	public static SelectorWokenUp newCase(Object aSource, Selector aSelector) {    	
		String aMessage =  aSelector.toString();
		SelectorWokenUp retVal = new SelectorWokenUp(aMessage, aSource, aSelector);
    	retVal.announce();
    	return retVal;
	}
}
