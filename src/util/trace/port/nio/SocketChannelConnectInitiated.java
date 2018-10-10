package util.trace.port.nio;

import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class SocketChannelConnectInitiated extends SocketChannelInfo {	
	public SocketChannelConnectInitiated(String aMessage, Object aFinder,
			SocketChannel aSocketChannel, SocketAddress aSocketAddress) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static SocketChannelConnectInitiated newCase(Object aSource, 			
			SocketChannel aSocketChannel, SocketAddress aSocketAddress) {    	
		String aMessage = 
				aSocketChannel.toString();
		SocketChannelConnectInitiated retVal = new SocketChannelConnectInitiated(aMessage, 
				aSource, 
				aSocketChannel,
				aSocketAddress);
    	retVal.announce();
    	return retVal;
	}
}
