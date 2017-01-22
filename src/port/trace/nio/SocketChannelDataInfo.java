package port.trace.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import port.trace.ReceivedCallEnded;
import util.trace.TraceableInfo;

public class SocketChannelDataInfo extends SocketChannelInfo {	
	protected ByteBuffer byteBuffer;
	public SocketChannelDataInfo(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer) {
		super(aMessage, aFinder, aSocketChannel);
		byteBuffer = aByteBuffer;
	}
	public static SocketChannelDataInfo newCase(Object aSource, 			
			SocketChannel aSocketChannel, ByteBuffer aByteBuffer) {    	
		String aMessage = "Thread:" + Thread.currentThread() + ", " +
				", Socket Channel:" + aSocketChannel + 
				", Byte Buffer:" + aByteBuffer;
		SocketChannelDataInfo retVal = new SocketChannelDataInfo(aMessage, 
				aSource, aSocketChannel, aByteBuffer);
    	retVal.announce();
    	return retVal;
	}
}
