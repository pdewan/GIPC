package util.trace.port.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SocketChannelWritten extends TraceableInfo {	
	public SocketChannelWritten(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder);
	}
	public static SocketChannelWritten newCase(Object aSource, 			
			SocketChannel aSocketChannel, ByteBuffer aByteBuffer) {    	
		String aMessage =
				aByteBuffer +
				" " +
				 aSocketChannel;
		SocketChannelWritten retVal = new SocketChannelWritten(aMessage, 
				aSource, aSocketChannel, aByteBuffer);
    	retVal.announce();
    	return retVal;
	}
}
