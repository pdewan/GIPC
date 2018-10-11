package util.trace.port.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelHeaderRead extends SocketChannelDataInfo {	
	public SocketChannelHeaderRead(String aMessage, Object aFinder,
			SocketChannel aSocketChannel,
			ByteBuffer aByteBuffer,
			int aLength) {
		super(aMessage, aFinder,  aSocketChannel, aByteBuffer);
	}
	public static SocketChannelHeaderRead newCase(Object aSource, 			
			SocketChannel aSocketChannel, ByteBuffer aByteBuffer, int aLength) {    	
		String aMessage = 
				"(" + aLength + ")" +
				aByteBuffer +
				" " +
				 aSocketChannel; 
				
		SocketChannelHeaderRead retVal = new SocketChannelHeaderRead(aMessage, 
				aSource, aSocketChannel, aByteBuffer, aLength);
    	retVal.announce();
    	return retVal;
	}
}
