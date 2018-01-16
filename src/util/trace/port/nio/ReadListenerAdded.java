package util.trace.port.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import inputport.nio.manager.SocketChannelAcceptListener;
import inputport.nio.manager.SocketChannelReadListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class ReadListenerAdded extends SocketChannelInfo {	
	public ReadListenerAdded(String aMessage, Object aFinder, 
			SocketChannel aSocketChannel, 
			SocketChannelReadListener aListener) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static ReadListenerAdded newCase(Object aSource, 			
			SocketChannel aSocketChannel, SocketChannelReadListener aListener) {    	
		String aMessage = 
				aListener.toString() + "<-" + aSocketChannel;
		ReadListenerAdded retVal = new ReadListenerAdded(aMessage, 
				aSource, 
				aSocketChannel,
				aListener);
    	retVal.announce();
    	return retVal;
	}
}
