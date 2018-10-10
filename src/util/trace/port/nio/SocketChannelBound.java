package util.trace.port.nio;

import java.net.InetSocketAddress;
import java.nio.channels.spi.AbstractSelectableChannel;

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
