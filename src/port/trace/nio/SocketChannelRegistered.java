package port.trace.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import port.trace.ReceivedCallEnded;
import util.trace.TraceableInfo;

public class SocketChannelRegistered extends SocketChannelInfo {	
	public SocketChannelRegistered(String aMessage, Object aFinder, 
			SocketChannel aSocketChannel, Selector aSelector, int aNewMask) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static SocketChannelRegistered newCase(Object aSource, 			
			SocketChannel aSocketChannel, Selector aSelector, int aNewMask) {    	
		String aMessage = 
				"Ops:" + aNewMask + ",  Selector:" + aSelector + ", " +
				aSocketChannel ;
				     			
		SocketChannelRegistered retVal = new SocketChannelRegistered(aMessage, aSource, aSocketChannel, aSelector, aNewMask);
    	retVal.announce();
    	return retVal;
	}
}
