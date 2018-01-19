package util.trace.port.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class ListenableAcceptsEnabled extends SocketChannelInfo {	
	public ListenableAcceptsEnabled(String aMessage, Object aFinder, 
			ServerSocketChannel aSocketServerChannel, 
			SocketChannelAcceptListener... aListener) {
		super(aMessage, aFinder, aSocketServerChannel);
	}
	public static ListenableAcceptsEnabled newCase(Object aSource, 			
			ServerSocketChannel aServerSocketChannel, SocketChannelAcceptListener... aListener) {    	
		String aMessage = 
				Arrays.toString(aListener) + "<-" + aServerSocketChannel;
		ListenableAcceptsEnabled retVal = new ListenableAcceptsEnabled(aMessage, 
				aSource, 
				aServerSocketChannel,
				aListener);
    	retVal.announce();
    	return retVal;
	}
}
