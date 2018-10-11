package util.trace.port.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelPartialMessageFound extends SocketChannelDataInfo {	
	public SocketChannelPartialMessageFound(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer,
			int aLength) {
		super(aMessage, aFinder,  aSocketChannel, aByteBuffer);
	}
	public static SocketChannelPartialMessageFound newCase(Object aSource, 			
			SocketChannel aSocketChannel, ByteBuffer aByteBuffer, int aLength) {    	
		String aMessage = 
				"(" + aLength + ")" +
				aByteBuffer +
				" " +
				 aSocketChannel; 
				
		SocketChannelPartialMessageFound retVal = new SocketChannelPartialMessageFound(aMessage, 
				aSource, aSocketChannel, aByteBuffer, aLength);
    	retVal.announce();
    	return retVal;
	}
}
