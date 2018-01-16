package util.trace.port.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.spi.AbstractSelectableChannel;

import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SelectorRequestNextInterestOp extends TraceableInfo {	
	public SelectorRequestNextInterestOp(String aMessage, Object aFinder, AbstractSelectableChannel aChannel, int aNewMask) {
		super(aMessage, aFinder);
	}
	public static SelectorRequestNextInterestOp newCase(Object aSource, AbstractSelectableChannel aChannel, int aNewMask) {    	
		String aMessage = "Ops:" + aNewMask + " Channel:" + aChannel ;     			
		SelectorRequestNextInterestOp retVal = new SelectorRequestNextInterestOp(aMessage, aSource, aChannel, aNewMask);
    	retVal.announce();
    	return retVal;
	}
}
