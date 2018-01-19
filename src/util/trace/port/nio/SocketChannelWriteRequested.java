package util.trace.port.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import inputport.nio.manager.listeners.SocketChannelWriteListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SocketChannelWriteRequested extends TraceableInfo {	
	public SocketChannelWriteRequested(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer,
			SocketChannelWriteListener... listeners) {
		super(aMessage, aFinder);
	}
	public static SocketChannelWriteRequested newCase(Object aSource, 			
			SocketChannel aSocketChannel, ByteBuffer aByteBuffer, SocketChannelWriteListener... listeners) {    	
		String aMessage =
				aByteBuffer + ", " + Arrays.toString(listeners) +
				" " +
				 aSocketChannel;
		SocketChannelWriteRequested retVal = new SocketChannelWriteRequested(aMessage, 
				aSource, aSocketChannel, aByteBuffer);
    	retVal.announce();
    	return retVal;
	}
}
