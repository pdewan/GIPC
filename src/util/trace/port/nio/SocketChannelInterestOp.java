package util.trace.port.nio;

import java.nio.channels.SelectionKey;
import java.util.HashMap;
import java.util.Map;

import util.trace.TraceableInfo;

public class SocketChannelInterestOp extends TraceableInfo {	
	protected static Map<Integer, String> interestOpToString;
	public SocketChannelInterestOp(String aMessage, Object aFinder, SelectionKey aSelectionKey, int aNewMask) {
		super(aMessage, aFinder);
	}
	public static SocketChannelInterestOp newCase(Object aSource, SelectionKey aSelectionKey, int aNewMask) {    	
		
		String aMessage = "Ops:" + toStringInterestOp(aNewMask) + " Selection Key:" + aSelectionKey ;     			
		SocketChannelInterestOp retVal = new SocketChannelInterestOp(aMessage, aSource, aSelectionKey, aNewMask);
    	retVal.announce();
    	return retVal;
	}
	public static String toStringInterestOp(int anInterestOp) {
		if (interestOpToString == null) {
			interestOpToString = new HashMap<>();
			interestOpToString.put(0, "NO_OP");
			interestOpToString.put(SelectionKey.OP_ACCEPT, "OP_ACCEPT");
			interestOpToString.put(SelectionKey.OP_CONNECT, "OP_CONNECT");
			interestOpToString.put(SelectionKey.OP_READ, "OP_READ");
			interestOpToString.put(SelectionKey.OP_WRITE, "OP_WRITE");
		}
		String retVal = interestOpToString.get(anInterestOp);
		if (retVal == null) {
			retVal = Integer.toString(anInterestOp);
		}
		return retVal;
	}
}
