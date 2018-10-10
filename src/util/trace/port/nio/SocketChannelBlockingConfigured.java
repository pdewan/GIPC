package util.trace.port.nio;

import java.nio.channels.spi.AbstractSelectableChannel;

public class SocketChannelBlockingConfigured extends SocketChannelInfo {	
	public SocketChannelBlockingConfigured(String aMessage, Object aFinder,
			AbstractSelectableChannel aSocketChannel,
			boolean aNewValue) {
		super(aMessage, aFinder,  aSocketChannel);
	}
	public static SocketChannelBlockingConfigured newCase(Object aSource, 			
			AbstractSelectableChannel aSocketChannel, boolean aNewValue) {    	
		String aMessage =
				aNewValue +
				" " +
				 aSocketChannel; 
				
		SocketChannelBlockingConfigured retVal = new SocketChannelBlockingConfigured(aMessage, 
				aSource, aSocketChannel, aNewValue);
    	retVal.announce();
    	return retVal;
	}
}
