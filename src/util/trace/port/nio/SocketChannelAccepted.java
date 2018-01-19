package util.trace.port.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SocketChannelAccepted extends SocketChannelInfo {	
	public SocketChannelAccepted(String aMessage, Object aFinder,
			SocketChannel aSocketChannel, ServerSocketChannel aSocketServerChannel, List<SocketChannelAcceptListener> aListeners) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static SocketChannelAccepted newCase(Object aSource, 			
			SocketChannel aSocketChannel, ServerSocketChannel aServerSocketChannel, List<SocketChannelAcceptListener> aListeners) {    	
		String aMessage = 
				aListeners + "<-" +
				aSocketChannel.toString() + ":" + aServerSocketChannel;
		SocketChannelAccepted retVal = new SocketChannelAccepted(aMessage, 
				aSource, 
				aSocketChannel,
				aServerSocketChannel,
				aListeners);
    	retVal.announce();
    	return retVal;
	}
}
