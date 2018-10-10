package util.trace.port.nio;

import java.nio.channels.ServerSocketChannel;
import java.util.Arrays;

import inputport.nio.manager.listeners.SocketChannelAcceptListener;

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
