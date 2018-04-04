package util.trace.port.nio;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SocketChannelConnectRequested extends SocketChannelInfo {	
	public SocketChannelConnectRequested(String aMessage, Object aFinder,
			SocketChannel aSocketChannel, InetAddress aHost, int aPort, SocketChannelConnectListener... listeners) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static SocketChannelConnectRequested newCase(Object aSource, 			
			SocketChannel aSocketChannel, InetAddress aHost, int aPort, SocketChannelConnectListener... listeners) {    	
		String aMessage = "(" + aHost + ":" + aPort + "," + Arrays.toString(listeners) + ") <-" +
				aSocketChannel.toString();
		SocketChannelConnectRequested retVal = new SocketChannelConnectRequested(aMessage, 
				aSource, 
				aSocketChannel,
				aHost,
				aPort,
				listeners);
    	retVal.announce();
    	return retVal;
	}
}
