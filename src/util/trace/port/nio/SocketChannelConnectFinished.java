package util.trace.port.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

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
