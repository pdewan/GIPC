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
import inputport.nio.manager.WriteBoundedBufferListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class WriteListenerAdded extends SocketChannelInfo {	
	public WriteListenerAdded(String aMessage, Object aFinder, 
			SocketChannel aSocketChannel, 
			WriteBoundedBufferListener aListener) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static WriteListenerAdded newCase(Object aSource, 			
			SocketChannel aSocketChannel, WriteBoundedBufferListener aListener) {    	
		String aMessage = 
				aListener.toString() + "<-" + aSocketChannel;
		WriteListenerAdded retVal = new WriteListenerAdded(aMessage, 
				aSource, 
				aSocketChannel,
				aListener);
    	retVal.announce();
    	return retVal;
	}
}
