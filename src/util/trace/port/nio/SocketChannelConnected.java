package util.trace.port.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;

import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SocketChannelConnected extends TraceableInfo {	
	public SocketChannelConnected(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,List<SocketChannelConnectListener> aListeners) {
		super(aMessage, aFinder);
	}
	public static SocketChannelConnected newCase(Object aSource, 			
			SocketChannel aSocketChannel,
			List<SocketChannelConnectListener> aListeners) {    	
		String aMessage = aListeners + "<-" + 
				aSocketChannel.toString() ;
		SocketChannelConnected retVal = new SocketChannelConnected(aMessage, 
				aSource, 
				aSocketChannel,
				aListeners);
    	retVal.announce();
    	return retVal;
	}
}
