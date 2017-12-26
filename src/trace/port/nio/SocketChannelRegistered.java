package trace.port.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

import inputport.rpc.RemoteCall;
import trace.port.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class SocketChannelRegistered extends SocketChannelInfo {	
	public SocketChannelRegistered(String aMessage, Object aFinder, 
			AbstractSelectableChannel aSocketChannel, Selector aSelector, int aNewMask) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static SocketChannelRegistered newCase(Object aSource, 			
			AbstractSelectableChannel aSocketChannel, Selector aSelector, int aNewMask) {    	
		String aMessage = 
				"Ops:" + aNewMask + ",  Selector:" + aSelector + ", " +
				aSocketChannel ;
				     			
		SocketChannelRegistered retVal = new SocketChannelRegistered(aMessage, aSource, aSocketChannel, aSelector, aNewMask);
    	retVal.announce();
    	return retVal;
	}
}
