package util.trace.port.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SelectCalled extends TraceableInfo {	
	protected Selector command;
	public SelectCalled(String aMessage, Object aFinder,
			Selector aSelector) {
		super(aMessage, aFinder);
		command = aSelector;
	}
	public static SelectCalled newCase(Object aSource, Selector aSelector) {    	
		String aMessage =  aSelector.toString();
		SelectCalled retVal = new SelectCalled(aMessage, aSource, aSelector);
    	retVal.announce();
    	return retVal;
	}
}
