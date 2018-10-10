package util.trace.port.nio;

import java.nio.channels.SocketChannel;
import java.util.List;

import inputport.nio.manager.listeners.SocketChannelConnectListener;
import util.trace.TraceableInfo;

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
