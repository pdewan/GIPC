package util.trace.port.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;

import inputport.nio.manager.listeners.SocketChannelReadListener;
import inputport.rpc.RemoteCall;
import util.trace.TraceableInfo;
import util.trace.port.rpc.ReceivedCallEndedOld;

public class SocketChannelFullMessageRead extends SocketChannelDataInfo {	
	public SocketChannelFullMessageRead(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer,
			int aLength,
			List<SocketChannelReadListener> readListeners) {
		super(aMessage, aFinder,  aSocketChannel, aByteBuffer);
	}
	public static SocketChannelFullMessageRead newCase(Object aSource, 			
			SocketChannel aSocketChannel, ByteBuffer aByteBuffer, int aLength, List<SocketChannelReadListener> readListeners) {    	
		String aMessage = 
				readListeners + "<-" + 
				"(" + aLength + ")" +
				aByteBuffer +
				" " +
				 aSocketChannel; 
				
		SocketChannelFullMessageRead retVal = new SocketChannelFullMessageRead(aMessage, 
				aSource, aSocketChannel, aByteBuffer, aLength, readListeners);
    	retVal.announce();
    	return retVal;
	}
}
