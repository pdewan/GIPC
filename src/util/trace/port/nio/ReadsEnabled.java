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

public class ReadsEnabled extends SocketChannelInfo {	
	public ReadsEnabled(String aMessage, Object aFinder, 
			SocketChannel aSocketChannel) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static ReadsEnabled newCase(Object aSource, 			
			SocketChannel aSocketChannel) {    	
		String aMessage = 
				aSocketChannel.toString();
		ReadsEnabled retVal = new ReadsEnabled(aMessage, 
				aSource, 
				aSocketChannel);
    	retVal.announce();
    	return retVal;
	}
}
