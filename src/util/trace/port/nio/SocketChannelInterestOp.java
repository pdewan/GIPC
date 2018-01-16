package util.trace.port.nio;

import java.nio.channels.SelectionKey;

import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SocketChannelInterestOp extends TraceableInfo {	
	public SocketChannelInterestOp(String aMessage, Object aFinder, SelectionKey aSelectionKey, int aNewMask) {
		super(aMessage, aFinder);
	}
	public static SocketChannelInterestOp newCase(Object aSource, SelectionKey aSelectionKey, int aNewMask) {    	
		String aMessage = "Ops:" + aNewMask + " Selection Key:" + aSelectionKey ;     			
		SocketChannelInterestOp retVal = new SocketChannelInterestOp(aMessage, aSource, aSelectionKey, aNewMask);
    	retVal.announce();
    	return retVal;
	}
}
