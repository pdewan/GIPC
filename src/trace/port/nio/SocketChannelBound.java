package trace.port.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

import inputport.rpc.RemoteCall;
import trace.port.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class SocketChannelBound extends SocketChannelInfo {	
	public SocketChannelBound(String aMessage, Object aFinder,
			AbstractSelectableChannel aSocketChannel,
			InetSocketAddress aNewValue) {
		super(aMessage, aFinder,  aSocketChannel);
	}
	public static SocketChannelBound newCase(Object aSource, 			
			AbstractSelectableChannel aSocketChannel, InetSocketAddress aNewValue) {    	
		String aMessage =
				aNewValue +
				" " +
				 aSocketChannel; 
				
		SocketChannelBound retVal = new SocketChannelBound(aMessage, 
				aSource, aSocketChannel, aNewValue);
    	retVal.announce();
    	return retVal;
	}
}
