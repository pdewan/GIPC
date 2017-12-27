package trace.port.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

import inputport.rpc.RemoteCall;
import trace.port.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

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
