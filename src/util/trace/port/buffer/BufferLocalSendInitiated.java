package util.trace.port.buffer;

import java.nio.ByteBuffer;

import util.trace.TraceableInfo;

public class BufferLocalSendInitiated extends TraceableInfo {	
	public BufferLocalSendInitiated(String aMessage, Object aFinder,
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer,
			Object aBufferChannel) {
		super(aMessage, aFinder);
	}
	public static BufferLocalSendInitiated newCase(Object aFinder, 
			String aSource,
			String aDestination,
			ByteBuffer aByteBuffer,
			Object aBufferChannel) {    	
		String aMessage =
				aSource + "->" + aDestination + " " +
				
				aByteBuffer +
				"(" +
				 aBufferChannel + ")";
		BufferLocalSendInitiated retVal = new BufferLocalSendInitiated(aMessage, 
				aFinder,
				aSource, aDestination, aByteBuffer, aBufferChannel);
    	retVal.announce();
    	return retVal;
	}
}
