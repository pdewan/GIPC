package port.trace.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class SocketChannelRead extends SocketChannelDataInfo {	
	public SocketChannelRead(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder,  aSocketChannel, aByteBuffer);
	}
	public static SocketChannelRead newCase(Object aSource, 			
			SocketChannel aSocketChannel, ByteBuffer aByteBuffer) {    	
		String aMessage =
				aByteBuffer +
				" " +
				 aSocketChannel; 
				
		SocketChannelRead retVal = new SocketChannelRead(aMessage, 
				aSource, aSocketChannel, aByteBuffer);
    	retVal.announce();
    	return retVal;
	}
}
