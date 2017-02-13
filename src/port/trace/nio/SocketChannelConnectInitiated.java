package port.trace.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

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
