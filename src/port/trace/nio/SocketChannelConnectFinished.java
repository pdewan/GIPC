package port.trace.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import port.trace.ReceivedCallEnded;
import util.trace.TraceableInfo;

public class SocketChannelConnectFinished extends TraceableInfo {	
	public SocketChannelConnectFinished(String aMessage, Object aFinder,
			SocketChannel aSocketChannel) {
		super(aMessage, aFinder);
	}
	public static SocketChannelConnectFinished newCase(Object aSource, 			
			SocketChannel aSocketChannel) {    	
		String aMessage = 
				aSocketChannel.toString() ;
		SocketChannelConnectFinished retVal = new SocketChannelConnectFinished(aMessage, 
				aSource, 
				aSocketChannel);
    	retVal.announce();
    	return retVal;
	}
}
